/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.pojo.Cart;
import com.lpl.repository.ReceiptRepository;
import com.lpl.service.ReceiptService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepository;
    @Override
    public boolean addReceipt(Map<String, Cart> carts) {
        return this.receiptRepository.addReceipt(carts);
    }
    
}
