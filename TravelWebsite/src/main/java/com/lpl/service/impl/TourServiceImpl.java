/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lpl.pojo.Image;
import com.lpl.pojo.Tour;
import com.lpl.repository.TourRepository;
import com.lpl.service.TourService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Tour> getTours(Map<String, String> params) {
        return this.tourRepository.getTours(params);
    }

    @Override
    public int countTour(Map<String, String> params) {
        return this.tourRepository.countTour(params);
    }

    @Override
    public boolean addOrUpdateTour(Tour t, List<MultipartFile> files) {
//        if (!t.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(t.getFile().getBytes(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//
//                t.setTourImageurl(res.get("secure_url").toString());
//
//            } catch (IOException ex) {
//                Logger.getLogger(TourServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//       
//        return this.tourRepository.addOrUpdateTour(t);

        if (files != null && !files.isEmpty()) {
            List<Image> images = new ArrayList<>();
            try {
                files.forEach(file -> {
                    if (!file.isEmpty()) {
                        try {
                            Map res = this.cloudinary.uploader().upload(file.getBytes(),
                                    ObjectUtils.asMap("resource_type", "auto"));

                            String imageUrl = res.get("secure_url").toString();
                            System.out.println("Image URL: " + imageUrl);
                            Image img = new Image();
                            img.setImageUrl(imageUrl);
                            img.setTourId(t);
                                
                            images.add(img);
                        } catch (IOException ex) {
                            Logger.getLogger(TourServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                t.setImageSet(images);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return this.tourRepository.addOrUpdateTour(t);
    }

    @Override
    public Tour getTourById(int id
    ) {
        return this.tourRepository.getTourById(id);
    }

    @Override
    public boolean deleteTour(int id
    ) {
        return this.tourRepository.deleteTour(id);
    }
}
