/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.pojo.Comment;
import com.lpl.pojo.News;
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
    private UserRepository userRepository;

    @Override
    public List<Comment> getCommentsByTour(int tourId) {
        return this.commentRepository.getCommentsByTour(tourId);
    }

    @Override
    public List<Comment> getCommentsByNews(int newsId) {
        return this.commentRepository.getCommentsByNews(newsId);
    }

    @Override
    public int countComments(int tourId) {
        return this.commentRepository.countComments(tourId);
    }

    @Override
    public Comment addComment(Comment c) {
        c.setCommentDatetime(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Đã đăng nhập
            String currentPrincipalName = authentication.getName();
            System.out.println("USERNAME: " + currentPrincipalName);
            User user = this.userRepository.getUserByUsername(currentPrincipalName);
//            User user = this.userRepository.getUserByUsername("meo");
            c.setUserId(user);
            Tour tour = c.getTourId(); // Lấy đối tượng Tour từ Comment
            News news = c.getNewsId(); // Lấy đối tượng News từ Comment

            if (tour != null) {
                // Thêm bình luận cho tour
                return this.commentRepository.addComment(c, tour.getTourId(), 0);
            } else if (news != null) {
                // Thêm bình luận cho tin tức
                return this.commentRepository.addComment(c, 0, news.getNewsId());
            } else {
                return null;
            }
        } else {
            System.out.println("Không tìm thấy user");
        }
        return null;
    }

}
