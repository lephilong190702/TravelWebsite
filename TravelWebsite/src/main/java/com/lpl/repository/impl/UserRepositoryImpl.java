/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.User;
import com.lpl.repository.UserRepository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lephi
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Override
    public User addUser(User user) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.save(user);
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q = q.select(root);
        
        if(!username.isEmpty()){
            Predicate p = b.equal(root.get("userUsername").as(String.class), username.trim());
            q = q.where(p);
        }
//        q.where(b.equal(root.get("userUsername"), username));
        Query query = s.createQuery(q);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User  u = this.getUserByUsername(username);
        return this.passEncoder.matches(password, u.getUserPassword());
    }
}
