/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Cart;
import com.lpl.pojo.Tour;
import com.lpl.service.ReceiptService;
import com.lpl.service.TourService;
import com.lpl.service.UserService;
import com.lpl.service.impl.EmailServiceImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lephi
 */
@RestController
@RequestMapping("/api")
public class ApiReceiptController {

    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;

    @PostMapping("/pay/")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void addReceipt(@RequestBody Map<String, Cart> carts) {
        this.receiptService.addReceipt(carts);
        System.out.println("THANH TOAN THANH CONG");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String to = userService.findEmailByUserName(username);
        System.out.println("EMAIL " + to);
        String subject = "Travel Website";
        String text = "Payment sucessful. Here is your receipt";

        String htmlContent = generateReceiptHtml(carts);
        System.out.println("MA HTML " + htmlContent);

        System.out.println("CHUAN BI GUI MAIL ");
        emailService.sendEmail(to, subject, text, htmlContent, "receipt.pdf");
        System.out.println("GUI MAIL THANH CONG!");
    }

    public String generateReceiptHtml(Map<String, Cart> carts) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html><body>");
        htmlBuilder.append("<h1>Receipt</h1>");
        double totalPrice = 0;
        for (Map.Entry<String, Cart> entry : carts.entrySet()) {
            int tourId = Integer.parseInt(entry.getKey());
            Cart cartItem = entry.getValue();

            Tour t = tourService.getTourById(tourId);

            htmlBuilder.append("<p>Tour: ").append(t.getTourTitle()).append("</p>");
            htmlBuilder.append("<p>Adult quantity: ").append(cartItem.getAdultQuantity()).append("</p>");
            htmlBuilder.append("<p>Adult price: ").append(t.getTourAdultPrice()).append("</p>");
            htmlBuilder.append("<p>Children quantity: ").append(cartItem.getChildQuantity()).append("</p>");
            htmlBuilder.append("<p>Children price: ").append(t.getTourChildPrice()).append("</p>");
            double price = (t.getTourAdultPrice() * cartItem.getAdultQuantity()) + (t.getTourChildPrice() * cartItem.getChildQuantity());

            totalPrice += price;
            htmlBuilder.append("<hr>");
        }
        htmlBuilder.append("<p>Total Price: ").append(totalPrice).append("</p>");
        htmlBuilder.append("</body></html>");
        return htmlBuilder.toString();
    }

}
