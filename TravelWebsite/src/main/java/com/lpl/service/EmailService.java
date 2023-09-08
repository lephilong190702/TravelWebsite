/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

/**
 *
 * @author lephi
 */
public interface EmailService {
    void sendEmail(String to, String subject, String text,  String htmlContent, String attachmentFileName);
}
