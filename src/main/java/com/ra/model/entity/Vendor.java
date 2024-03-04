package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String image;
    @NotBlank(message = "Enter Vendor Name")
    private String vendorName;
    @NotBlank(message = "Enter Address")
    private String address;
    @Email(message = "Enter a valid email Address")
    private String email;
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b",message = "Enter a valid Vietnamese phone number")
    private String phone;

    @OneToMany(mappedBy = "vendor")
    @JsonIgnore
    List<Product> products;
    
}
