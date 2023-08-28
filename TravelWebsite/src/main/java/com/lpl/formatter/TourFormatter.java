/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.formatter;

import com.lpl.pojo.Tour;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author lephi
 */
public class TourFormatter implements Formatter<Tour> {

    @Override
    public String print(Tour t, Locale locale) {
        return String.valueOf(t.getTourId());
    }

    @Override
    public Tour parse(String tourId, Locale locale) throws ParseException {
        return new Tour(Integer.parseInt(tourId));
    }
    
}
