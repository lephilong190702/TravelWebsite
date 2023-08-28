/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Destination;
import com.lpl.service.DestinationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lephi
 */
@RestController
@RequestMapping("/api")
public class ApiDestinationController {
    @Autowired
    private DestinationService destinationService;
    @GetMapping("/destinations/")
    @CrossOrigin
    public ResponseEntity<List<Destination>> list() {
        return new ResponseEntity<>(this.destinationService.getDestinations(), HttpStatus.OK);
    }
}
