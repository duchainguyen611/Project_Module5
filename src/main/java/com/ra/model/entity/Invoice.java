package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.Enum.StatusInvoice;
import jakarta.persistence.*;
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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String serialNumber;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "statusInvoice")
    private StatusInvoice statusInvoice;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date createdAt;
    private Date receivedAt;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "invoice")
    @JsonIgnore
    private List<InvoiceDetail> invoiceDetails;
}
