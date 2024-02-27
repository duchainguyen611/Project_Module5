package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String serialNumber;
    private Double totalPrice;
    private Boolean statusOrders;
    private String note;

    @ManyToOne
    @JoinColumn(name = "vendorId",referencedColumnName = "id")
    private Vendor vendor;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    private List<OrderDetail> orderDetails;
}
