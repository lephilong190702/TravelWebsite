/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.lpl.service.EmailService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String text,  String htmlContent, String attachmentFileName) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(pdfOutputStream);
            try (PdfDocument pdfDocument = new PdfDocument(pdfWriter)) {
                ConverterProperties converterProperties = new ConverterProperties();
                HtmlConverter.convertToPdf(htmlContent, pdfDocument, converterProperties);
            }

            // Đính kèm tệp PDF vào email
            byte[] pdfBytes = pdfOutputStream.toByteArray();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
            helper.addAttachment(attachmentFileName, dataSource);

            javaMailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
