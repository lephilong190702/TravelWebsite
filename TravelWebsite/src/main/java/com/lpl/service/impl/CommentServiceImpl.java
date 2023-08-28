/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.pojo.Comment;
import com.lpl.pojo.Tour;
import com.lpl.pojo.User;
import com.lpl.repository.CommentRepository;
import com.lpl.repository.TourRepository;
import com.lpl.repository.UserRepository;
import com.lpl.service.CommentService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lephi
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Comment> getCommentsByTour(int tourId) {
        return this.commentRepository.getCommentsByTour(tourId);
    }

    @Override
    public int countComments(int tourId) {
        return this.commentRepository.countComments(tourId);
    }

    @Override
    public Comment addComment(String content, int tourId) {
        Comment c = new Comment();
        c.setCommentContent(content);
        c.setTourId(this.tourRepository.getTourById(tourId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
        if (authentication != null && authentication.isAuthenticated()) {
            // Đã đăng nhập
            String currentPrincipalName = authentication.getName();
            System.out.println("USERNAME: " + currentPrincipalName);
            User user = this.userRepository.getUserByUsername(currentPrincipalName);
//            User user = this.userRepository.getUserByUsername("meo");
            c.setUserId(user);
        } else {
            // Chưa đăng nhập
            System.out.println("Không tìm thấy user");
        }
        c.setCommentDatetime(new Date());
        return this.commentRepository.addComment(c);
    }
}
