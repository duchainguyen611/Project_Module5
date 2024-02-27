package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.Enum.RoleName;
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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private RoleName roleName;
}
