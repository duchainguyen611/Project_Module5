package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;

    private String email;
    private String image;
    private String fullName;
    private Boolean status;
    private String password;
    private Boolean sex;
    private String phone;
    private String address;
    private Date createdAt;
    private Date updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Invoice> invoiceList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<WishList> wishLists;

}
