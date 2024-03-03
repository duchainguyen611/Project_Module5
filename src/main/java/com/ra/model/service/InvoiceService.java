package com.ra.model.service;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.User;

import java.util.List;

public interface InvoiceService {
    Invoice findById(Long id);
    List<Invoice> findAllByUser(User user);
}
