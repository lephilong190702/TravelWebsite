/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.repository;

import com.lpl.pojo.User;
import java.util.List;

/**
 *
 * @author lephi
 */
public interface UserRepository {
    User addUser(User user);
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
}
