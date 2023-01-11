package com.asia.Shop.repository;

import com.asia.Shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, ProductEntity>
{
}
