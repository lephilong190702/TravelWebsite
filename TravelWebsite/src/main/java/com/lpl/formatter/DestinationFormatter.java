/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.formatter;

import com.lpl.pojo.Destination;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author lephi
 */
public class DestinationFormatter implements Formatter<Destination>{

    @Override
    public String print(Destination des, Locale locale) {
        return String.valueOf(des.getDestinationId());
    }

    @Override
    public Destination parse(String destinationId, Locale locale) throws ParseException {
        return new Destination(Integer.parseInt(destinationId));
    }
    
}
