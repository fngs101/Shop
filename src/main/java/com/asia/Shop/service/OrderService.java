package com.asia.Shop.service;

import com.asia.Shop.entity.OrderEntity;
import com.asia.Shop.entity.ProductEntity;
import com.asia.Shop.exception.OrderServiceException;
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
        if(order.getClientAddress() == null)
        {
            throw new OrderServiceException("No address specified");
        }
        if(order.getOrderNumber() == null)
        {
            throw new OrderServiceException("No order ID specified");
        }
        if(order.getClientName() == null)
        {
            throw new OrderServiceException("No client name specified");
        }
        orderRepository.save(order);
    }

    public List<OrderEntity> getOrders()
    {
        return orderRepository.findAll();
    }

    public void updateOrder(OrderEntity orderEntity)
    {
        orderRepository.findById(orderEntity.getId()).orElseThrow(() -> new OrderServiceException("There is no order with such ID"));

        orderRepository.save(orderEntity);
    }

    public void deleteOrder(Long id)
    {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderServiceException("There is no order with such ID"));
        orderRepository.delete(orderEntity);
    }
}
