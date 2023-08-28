/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.repository;



import com.lpl.pojo.Tour;
import java.util.List;
import java.util.Map;


/**
 *
 * @author lephi
 */
public interface TourRepository {
    List<Tour> getTours(Map<String, String> params);
    int countTour(Map<String, String> params);
    boolean addOrUpdateTour(Tour t);
    Tour getTourById(int id);
    boolean deleteTour(int id);
}
