package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer orderQuantity;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;
}
