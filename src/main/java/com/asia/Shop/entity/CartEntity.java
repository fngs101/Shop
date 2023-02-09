package com.asia.Shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch= FetchType.EAGER)
    private List<CartProductEntity> cartProducts;
}
