/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Comment;
import com.lpl.service.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lephi
 */
@RestController
@RequestMapping("/api")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/tours/{id}/comments")
    @CrossOrigin
    public ResponseEntity<List<Comment>> getComments(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.commentService.getCommentsByTour(id), HttpStatus.OK);
    }

    @PostMapping(path = "/tours/{id}/comments", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> params) {
        System.out.println("[DEBUG] - Account type: " + SecurityContextHolder.getContext().getAuthentication().getName());
        String content = params.get("commentContent");
        System.out.println("content:" + content);
        String string = params.get("tourId");
        int tourId = -1;
        if (string != null) {
            tourId = Integer.parseInt(string);
        }
        System.out.println("TOURID = " + tourId);
        Comment c = this.commentService.addComment(content, tourId);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
