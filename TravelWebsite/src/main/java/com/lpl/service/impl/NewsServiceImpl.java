/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lpl.pojo.News;
import com.lpl.repository.NewsRepository;
import com.lpl.service.NewsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<News> getNews(Map<String, String> params) {
        return this.newsRepository.getNews(params);
    }

    @Override
    public int countNews(Map<String, String> params) {
        return this.newsRepository.countNews(params);
    }

    @Override
    public boolean addOrUpdateNews(News n) {
        if (!n.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(n.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));

                n.setNewsImageurl(res.get("secure_url").toString());

            } catch (IOException ex) {
                Logger.getLogger(NewsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.newsRepository.addOrUpdateNews(n);
    }

    @Override
    public News getNewById(int id) {
        return this.newsRepository.getNewById(id);
    }

    @Override
    public boolean deleteNew(int id) {
        return this.newsRepository.deleteNew(id);
    }

}
