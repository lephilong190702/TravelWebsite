/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.User;
import com.lpl.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
@Controller
public class UserController {

    @Autowired
    private UserService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @RequestParam Map<String, String> params, @ModelAttribute(value = "user") User user,
            @Valid User u, BindingResult rs, @RequestPart MultipartFile file) {
        if (rs.hasErrors()) {
            return "register";
        }

        if (!user.getUserPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errMsg", "Mật khẩu xác nhận không khớp với mật khẩu");
            return "register";
        }

        User newUser = userDetailsService.addUser(params, file);
        if(newUser != null)
            return "redirect:/login";
        else
            return "register";
    }
}
