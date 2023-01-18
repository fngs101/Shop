package com.asia.Shop.controller;

import com.asia.Shop.entity.OrderEntity;
import com.asia.Shop.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class OrderController
{
    private OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("api/orders")
    public List<OrderEntity> getOrders()
    {
        return orderService.getOrders();
    }

    @PostMapping("api/orders")
    public void addOrder(@RequestBody OrderEntity orderEntity)
    {
        orderService.addOrder(orderEntity);
    }

    @PutMapping("api/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderEntity)
    {
        orderEntity.setId(id);
        try
        {
            orderService.updateOrder(orderEntity);
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/orders/{id}")
    public void deleteOrder(@PathVariable Long id)
    {
        orderService.deleteOrder(id);
    }
}
