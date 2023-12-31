/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.Booking;
import com.lpl.pojo.Destination;
import com.lpl.pojo.Payment;
import com.lpl.pojo.Tour;
import com.lpl.repository.StatsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lephi
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> countTourByDestination(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rT = q.from(Tour.class);
        Root rD = q.from(Destination.class);

        q.multiselect(rD.get("destinationId"), 
                rD.get("destinationCity"), 
                b.function("MONTH", Integer.class, rT.get("tourStartdate")),
                b.function("QUARTER", Integer.class, rT.get("tourStartdate")),
                b.function("YEAR", Integer.class, rT.get("tourStartdate")),
                b.count(rT.get("tourId")));
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rT.get("destinationId"), rD.get("destinationId")));

        String years = params.get("years"); 
        if (years != null && !years.isEmpty()) {
            predicates.add(b.equal(b.function("YEAR", Integer.class, rT.get("tourStartdate")), Integer.parseInt(years)));
        }

        String quarters = params.get("quarters");
        if (quarters != null && !quarters.isEmpty()) {
            if (years != null && !years.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        b.equal(b.function("YEAR", Integer.class, rT.get("tourStartdate")), Integer.parseInt(years)),
                        b.equal(b.function("QUARTER", Integer.class, rT.get("tourStartdate")), Integer.parseInt(quarters))
                ));
            }
        }

        String months = params.get("months");
        if (months != null && !months.isEmpty()) {
            if (years != null && !years.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        b.equal(b.function("YEAR", Integer.class, rT.get("tourStartdate")), Integer.parseInt(years)),
                        b.equal(b.function("MONTH", Integer.class, rT.get("tourStartdate")), Integer.parseInt(months))
                ));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(rD.get("destinationId"), 
                b.function("MONTH", Integer.class, rT.get("tourStartdate")),
                b.function("QUARTER", Integer.class, rT.get("tourStartdate")),
                b.function("YEAR", Integer.class, rT.get("tourStartdate"))
        );
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsRevenue(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rT = q.from(Tour.class);
        Root rB = q.from(Booking.class);
        Root rP = q.from(Payment.class);

        q.multiselect(
                b.function("MONTH", Integer.class, rP.get("paymentCreatedate")),
                b.function("QUARTER", Integer.class, rP.get("paymentCreatedate")),
                b.function("YEAR", Integer.class, rP.get("paymentCreatedate")),
                b.sum(
                        b.sum(
                                b.prod(rB.get("bookingAdultunitprice"), rB.get("bookingNumberofaudult")),
                                b.prod(rB.get("bookingChildunitprice"), rB.get("bookingNumberofchidren"))
                        )
                )
        );

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rB.get("tourId"), rT.get("tourId")));
        predicates.add(b.equal(rB.get("paymentId"), rP.get("paymentId")));

        String fd = params.get("fromDate");
        {
            if (fd != null && !fd.isEmpty())
        try {
                predicates.add(b.greaterThanOrEqualTo(rP.get("paymentCreatedate"), FORMAT.parse(fd)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String td = params.get("toDate");
        if (td != null && !td.isEmpty()) {
            try {
                predicates.add(b.lessThanOrEqualTo(rP.get("paymentCreatedate"), FORMAT.parse(td)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String year = params.get("year");
        if (year != null && !year.isEmpty()) {
            predicates.add(b.equal(b.function("YEAR", Integer.class, rP.get("paymentCreatedate")), Integer.parseInt(year)));
        }

        String quarter = params.get("quarter");
        if (quarter != null && !quarter.isEmpty()) {
            if (year != null && !year.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        b.equal(b.function("YEAR", Integer.class, rP.get("paymentCreatedate")), Integer.parseInt(year)),
                        b.equal(b.function("QUARTER", Integer.class, rP.get("paymentCreatedate")), Integer.parseInt(quarter))
                ));
            }
        }
        String month = params.get("month");
        if (month != null && !month.isEmpty()) {
            if (year != null && !year.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        b.equal(b.function("YEAR", Integer.class, rP.get("paymentCreatedate")), Integer.parseInt(year)),
                        b.equal(b.function("MONTH", Integer.class, rP.get("paymentCreatedate")), Integer.parseInt(month))
                ));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));

        q.groupBy(
                b.function("MONTH", Integer.class, rP.get("paymentCreatedate")),
                b.function("QUARTER", Integer.class, rP.get("paymentCreatedate")),
                b.function("YEAR", Integer.class, rP.get("paymentCreatedate"))
        );
        Query query = s.createQuery(q);

        return query.getResultList();
    }

}
