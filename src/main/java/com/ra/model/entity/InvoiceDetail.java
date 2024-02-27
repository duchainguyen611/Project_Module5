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
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Double unitPrice;
    private Integer invoiceQuantity;

    @ManyToOne
    @JoinColumn(name = "invoiceId",referencedColumnName = "id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;
}
