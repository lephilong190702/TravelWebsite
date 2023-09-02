/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.repository.StatsRepository;
import com.lpl.service.StatsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> countTourByDestination(Map<String, String> params) {
        List<Object[]> count = this.statsRepository.countTourByDestination(params);
        return count;
    }

    @Override
    public List<Object[]> statsRevenue(Map<String, String> params) {
        return this.statsRepository.statsRevenue(params);
    }

}
