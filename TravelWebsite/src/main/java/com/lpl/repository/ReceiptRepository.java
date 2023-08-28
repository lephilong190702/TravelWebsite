/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.repository;

import com.lpl.pojo.Cart;
import java.util.Map;

/**
 *
 * @author lephi
 */
public interface ReceiptRepository {
    boolean addReceipt(Map<String, Cart> carts);
}
