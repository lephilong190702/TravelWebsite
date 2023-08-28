/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.pojo.Destination;
import com.lpl.repository.DestinationRepository;
import com.lpl.service.DestinationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class DestinationServiceImpl implements DestinationService{
    @Autowired
    public DestinationRepository destinationRepository;
    @Override
    public List<Destination> getDestinations() {
        return this.destinationRepository.getDestinations();
    }
    
}
