/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.Image;
import com.lpl.pojo.Tour;
import com.lpl.repository.TourRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lephi
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TourRepositoryImpl implements TourRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Tour> getTours(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tour> q = b.createQuery(Tour.class);
        Root root = q.from(Tour.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tourTitle"), String.format("%%%s%%", kw)));
            }
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(b.greaterThanOrEqualTo(root.get("tourPrice"), Float.parseFloat(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(b.lessThanOrEqualTo(root.get("tourPrice"), Float.parseFloat(toPrice)));
            }

            String fromDate = params.get("fromDate");
            if (fromDate != null && !fromDate.isEmpty()) {
                try {
                    predicates.add(b.greaterThanOrEqualTo(root.get("tourStartdate"), FORMAT.parse(fromDate)));
                } catch (ParseException ex) {
                    Logger.getLogger(TourRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String toDate = params.get("toDate");
            if (toDate != null && !toDate.isEmpty()) {
                try {
                    predicates.add(b.lessThanOrEqualTo(root.get("tourEnddate"), FORMAT.parse(toDate)));
                } catch (ParseException ex) {
                    Logger.getLogger(TourRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String desId = params.get("desId");
            if (desId != null && !desId.isEmpty()) {
                predicates.add(b.equal(root.get("destinationId"), Integer.parseInt(desId)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public int countTour(Map<String, String> params) {
        Session s = this.factory.getObject().openSession();
        Query q = s.createQuery("SELECT Count(*) from Tour");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateTour(Tour t) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (t.getTourId() == null) {
                s.save(t);
            } else {
                s.update(t);
            }
            
            List<Image> images = t.getImageSet();
            if (images != null && !images.isEmpty()) {
                images.forEach(img -> {
                    s.saveOrUpdate(img);
                });
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Tour getTourById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tour.class, id);
    }

    @Override
    public boolean deleteTour(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Tour t = this.getTourById(id);
        try {
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
