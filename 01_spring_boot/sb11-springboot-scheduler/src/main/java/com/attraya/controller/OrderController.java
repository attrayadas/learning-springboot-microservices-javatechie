package com.attraya.controller;

import com.attraya.model.Order;
import com.attraya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Send POST request in the middle of scheduled job to get dynamic rows in the Excel sheet in email
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
}
