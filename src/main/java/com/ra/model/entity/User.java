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
    @NotBlank(message = "Enter User Name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+",message = "No character special")
    private String username;
    @NotBlank(message = "Enter Email")
    @Email(message = "Enter a valid email Address")
    private String email;
    private String image;
    @NotBlank(message = "Enter Full Name")
    private String fullName;
    private Boolean status;
    @Length(min = 6,message = "Passwords must be at least 6 characters")
    private String password;
    private Boolean sex;
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b",message = "Enter a valid Vietnamese phone number")
    private String phone;
    @NotBlank(message = "Enter Address")
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
