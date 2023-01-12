package com.asia.Shop.repository;

import com.asia.Shop.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long>
{
}
