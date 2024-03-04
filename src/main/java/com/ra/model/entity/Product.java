package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String sku;
    @NotBlank(message = "Enter Product Name")
    private String productName;
    @NotBlank(message = "Enter Description")
    private String description;
    @NotBlank(message = "Enter Unit Price")
    private Double unitPrice;
    @NotBlank(message = "Enter Stock Quantity")
    private Integer stockQuantity;
    private String image;
    private Date createdAt;
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vendorId", referencedColumnName = "id")
    private Vendor vendor;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<InvoiceDetail> invoiceDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ShoppingCart> shoppingCarts;
}

