/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

import com.lpl.pojo.Tour;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
public interface TourService {
     List<Tour> getTours(Map<String, String> params);
     int countTour(Map<String, String> params);
     boolean addOrUpdateTour(Tour t, List<MultipartFile> files);
     Tour getTourById(int id);
     boolean deleteTour(int id);
}
