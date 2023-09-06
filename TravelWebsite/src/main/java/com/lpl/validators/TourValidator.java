///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.lpl.validators;
//
//import com.lpl.pojo.Tour;
//import org.springframework.stereotype.Component;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
///**
// *
// * @author lephi
// */
//@Component
//public class TourValidator implements Validator
//{
//    
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Tour.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Tour t = (Tour) target;
//        if(!t.getTourTitle().contains("LPL")) 
//                errors.rejectValue("tourTitle", "tour.tourTitle.myErr");
//    }
//    
//}
