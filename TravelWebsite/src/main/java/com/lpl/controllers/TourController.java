/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Tour;
import com.lpl.service.TourService;
import com.lpl.validators.WebAppValidator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
@Controller
@PropertySource("classpath:configs.properties")
public class TourController {

    @Autowired
    private TourService tourService;
   

    @GetMapping("/admin/tours")
    public String list(Model model) {
        model.addAttribute("tour", new Tour());
        return "tour";
    }

    @GetMapping("/admin/tours/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("tour", this.tourService.getTourById(id));
        return "tour";
    }

    @PostMapping("/admin/tours")
    public String add(@ModelAttribute(value = "tour") @Valid Tour t,
            BindingResult rs, @RequestPart List<MultipartFile> files) {
        if (!rs.hasErrors()) {
            if (this.tourService.addOrUpdateTour(t, files) == true) {
                return "redirect:/";
            }
        }
        return "tour";
    }
}
