/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

import com.lpl.pojo.News;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lephi
 */
public interface NewsService {
    List<News> getNews(Map<String, String> params);
    int countNews(Map<String, String> params);
    boolean addOrUpdateNews(News n);
    News getNewById(int id);
    boolean deleteNew(int id);
}
