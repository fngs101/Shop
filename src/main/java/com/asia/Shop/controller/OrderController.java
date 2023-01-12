package com.asia.Shop.controller;

import com.asia.Shop.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController
{
    private OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }
}
