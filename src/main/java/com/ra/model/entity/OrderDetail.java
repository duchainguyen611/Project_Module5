package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Double unitPrice;
    private Integer orderQuantity;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Orders orders;
}
