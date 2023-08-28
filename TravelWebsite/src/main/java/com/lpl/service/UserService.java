/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

import com.lpl.pojo.User;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
public interface UserService extends UserDetailsService{
    User addUser(Map<String, String> params, MultipartFile avatar);
    User getUserByUsername(String username);   
    boolean authUser(String username, String password);
}
