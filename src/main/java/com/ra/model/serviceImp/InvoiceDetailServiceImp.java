package com.ra.model.serviceImp;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.InvoiceDetail;
import com.ra.model.repository.InvoiceDetailRepository;
import com.ra.model.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceDetailServiceImp implements InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Override
    public List<InvoiceDetail> findAllByInvoice(Invoice invoice) {
        return invoiceDetailRepository.findAllByInvoice(invoice);
    }
}
