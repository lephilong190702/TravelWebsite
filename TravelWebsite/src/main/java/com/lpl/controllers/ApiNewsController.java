/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Comment;
import com.lpl.pojo.News;
import com.lpl.service.CommentService;
import com.lpl.service.NewsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lephi
 */
@RestController
@RequestMapping("/api")
public class ApiNewsController {
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping("/news/")
    @CrossOrigin
    public ResponseEntity<List<News>> list(@RequestParam Map<String, String> param) {
        return new ResponseEntity<>(this.newsService.getNews(param), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/news/{newsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<News> detail(@PathVariable(value = "newsId") int id) {
        return new ResponseEntity<>(this.newsService.getNewById(id), HttpStatus.OK);
    }
    
    @GetMapping("/news/{newsId}/comments/")
    @CrossOrigin
    public ResponseEntity<List<Comment>> listComment(@PathVariable(value = "newsId") int id) {
        return new ResponseEntity<>(this.commentService.getCommentsByNews(id), HttpStatus.OK);
    }

    
}
