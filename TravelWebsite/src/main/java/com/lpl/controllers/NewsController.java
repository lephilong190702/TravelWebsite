/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.News;
import com.lpl.service.NewsService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lephi
 */
@Controller
@PropertySource("classpath:configs.properties")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private Environment env;
    @RequestMapping("/news")
    public String news(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("news", this.newsService.getNews(params));
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.newsService.countNews(params);
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        
        return "news";
    } 
    
    @GetMapping("/admin/news")
    public String list(Model model) {
        model.addAttribute("new", new News());
        return "new";
    }
    
    @GetMapping("/admin/news/{id}")
    public String update(Model model, @PathVariable(value = "id")int id) {
        model.addAttribute("new", this.newsService.getNewById(id));
        return "new";
    }
    
    @PostMapping("/admin/news")
    public String add(@ModelAttribute(value = "new") @Valid News n,
            BindingResult rs) {
        if(!rs.hasErrors())
        {
            if (this.newsService.addOrUpdateNews(n) == true)
                return "redirect:/news";
        }
        
        return "new";
    }
}
