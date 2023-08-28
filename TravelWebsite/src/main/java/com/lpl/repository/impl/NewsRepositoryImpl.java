/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.News;
import com.lpl.repository.NewsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class NewsRepositoryImpl implements NewsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<News> getNews(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<News> q = b.createQuery(News.class);
        Root root = q.from(News.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("newsTitle"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);
        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int page = Integer.parseInt(p);
            int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

            query.setMaxResults(pageSize);
            query.setFirstResult((page - 1) * pageSize);
        }
        return query.getResultList();
    }

    @Override
    public int countNews(Map<String, String> params) {
        Session s = this.factory.getObject().openSession();
        Query q = s.createQuery("SELECT Count(*) from News");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateNews(News n) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (n.getNewsId() == null) {
                s.save(n);
            } else {
                s.update(n);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public News getNewById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(News.class, id);
    }

    @Override
    public boolean deleteNew(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        News n = this.getNewById(id);
        try {
            s.delete(n);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
