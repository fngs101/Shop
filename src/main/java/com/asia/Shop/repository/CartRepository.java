package com.asia.Shop.repository;

import com.asia.Shop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Long, CartEntity>
{
}
