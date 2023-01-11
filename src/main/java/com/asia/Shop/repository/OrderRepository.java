package com.asia.Shop.repository;

import com.asia.Shop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
}
