/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Tour;
import com.lpl.service.TourService;
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
public class ApiTourController {
    @Autowired
    private TourService tourService;
    
    @GetMapping("/tours/")
    @CrossOrigin
    public ResponseEntity<List<Tour>> list(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.tourService.getTours(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/tours/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Tour> detail(@PathVariable(value ="id") int id){
       Tour t = this.tourService.getTourById(id);
       return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
