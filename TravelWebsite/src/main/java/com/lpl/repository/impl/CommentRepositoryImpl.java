/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.repository.impl;

import com.lpl.pojo.Comment;
import com.lpl.pojo.Tour;
import com.lpl.repository.CommentRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;

    @Override
    public List<Comment> getCommentsByTour(int tourId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root root = q.from(Comment.class);
        q.select(root);

        q = q.where(b.equal(root.get("tourId"), tourId));
        q = q.orderBy(b.desc(root.get("commentDatetime")));

        Query query = s.createQuery(q);

//        int pageSize = Integer.parseInt(this.env.getProperty("COMMENT_PAGE"));
//        query.setMaxResults(pageSize);
//        query.setFirstResult((page - 1) * pageSize);
        return query.getResultList();
    }

    @Override
    public int countComments(int tourId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) from Comment Where tourId.tourId=:id");
        q.setParameter("id", tourId);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Comment addComment(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(c);
        
        return c;
    }

}
