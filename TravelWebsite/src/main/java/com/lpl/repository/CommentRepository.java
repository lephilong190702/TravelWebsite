/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.repository;

import com.lpl.pojo.Comment;
import java.util.List;

/**
 *
 * @author lephi
 */
public interface CommentRepository {
    List<Comment> getCommentsByTour(int tourId);
    int countComments(int tourId);
    Comment addComment(Comment c);
}
