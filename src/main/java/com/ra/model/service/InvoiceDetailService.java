package com.ra.model.service;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailService {
    List<InvoiceDetail> findAllByInvoice(Invoice invoice);
}
