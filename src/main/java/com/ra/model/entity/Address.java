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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String fullAddress;
    private String phone;
    private String receiveName;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
}
