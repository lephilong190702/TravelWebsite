/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lephi
 */
public interface StatsService {
    List<Object[]> countTourByDestination();
    List<Object[]> statsRevenue(Map<String, String> params);
}
