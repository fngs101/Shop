package com.asia.Shop.controller;

import com.asia.Shop.dto.ErrorDto;
import com.asia.Shop.entity.OrderEntity;
import com.asia.Shop.exception.OrderServiceException;
import com.asia.Shop.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<OrderEntity>> getOrders()
    {
        List<OrderEntity> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("api/orders")
    public ResponseEntity<ErrorDto> addOrder(@RequestBody OrderEntity orderEntity)
    {
        try
        {
            orderService.addOrder(orderEntity);
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        }
        catch(OrderServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(400));
        }

    }

    @PutMapping("api/orders/{id}")
    public ResponseEntity<ErrorDto> updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderEntity)
    {
        orderEntity.setId(id);
        try
        {
            orderService.updateOrder(orderEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(400));
        }

    }

    @DeleteMapping("api/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id)
    {
        try
        {
            orderService.deleteOrder(id);
            //uzywam tej wersji zeby dodatkowo message dac?
            return ResponseEntity.ok("Order deleted");
        }
        catch(OrderServiceException e)
        {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.valueOf(404));
        }
    }
}
