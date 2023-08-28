/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.Employee;
import com.lpl.repository.EmployeeRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class EmployeeRepositoryImpl implements EmployeeRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Employee> getEmployee() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Employee> q = b.createQuery(Employee.class);
        Root root = q.from(Employee.class);
        q.select(root);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public boolean addOrUpdateEmployee(Employee e) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (e.getEmployeeId() == null) {
                s.save(e);
            } else {
                s.update(e);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Employee.class, id);
    }

    @Override
    public boolean deleteEmployee(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Employee n = this.getEmployeeById(id);
        try {
            s.delete(n);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
