package com.asia.Shop.service;

import com.asia.Shop.entity.OrderEntity;
import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public void addOrder(OrderEntity order)
    {
        orderRepository.save(order);
    }

    public List<OrderEntity> getOrders()
    {
        return orderRepository.findAll();
    }

    public void updateOrder(OrderEntity orderEntity)
    {
        orderRepository.findById(orderEntity.getId()).orElseThrow();

        orderRepository.save(orderEntity);
    }

    public void deleteOrder(Long id)
    {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        orderRepository.delete(orderEntity);
    }
}
