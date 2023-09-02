///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.lpl.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lpl.service.StatsService;
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author lephi
// */
//@Controller
//public class StatController {
//
//    @Autowired
//    private StatsService statsService;
//
//    @RequestMapping("/stat")
//    public String revenue(Model model, @RequestParam Map<String, String> params) throws JsonProcessingException {
//        List<Object[]> revenueData = this.statsService.statsRevenue(params);
//        List<Object[]> countData = this.statsService.countTourByDestination(params);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String revenueJson = objectMapper.writeValueAsString(revenueData);
//        String countJson = objectMapper.writeValueAsString(countData);
//        model.addAttribute("revenue", revenueJson);
//        model.addAttribute("count", countJson);
//        return "stat";
//    }
//}
