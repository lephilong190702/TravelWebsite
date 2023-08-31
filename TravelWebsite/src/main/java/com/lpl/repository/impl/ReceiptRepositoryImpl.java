/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.Booking;
import com.lpl.pojo.Cart;
import com.lpl.pojo.Payment;
import com.lpl.repository.ReceiptRepository;
import com.lpl.repository.TourRepository;
import com.lpl.repository.UserRepository;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lephi
 */
@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        try {
            Payment payment = new Payment();
            payment.setUserId(this.userRepository.getUserByUsername(authentication.getName()));
            payment.setPaymentCreatedate(new Date());
            s.save(payment);

            for (Cart c : carts.values()) {
                Booking b = new Booking();
                b.setBookingAdultunitprice(c.getAdultUnitPrice());
                b.setBookingChildunitprice(c.getChildUnitPrice());
                b.setBookingNumberofaudult(c.getAdultQuantity());
                b.setBookingNumberofchidren(c.getChildQuantity());
                b.setPaymentId(payment);
                b.setTourId(this.tourRepository.getTourById(c.getId()));
                s.save(b);
            }
            return true;
        }catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

}
