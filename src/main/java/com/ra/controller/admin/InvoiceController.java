package com.ra.controller.admin;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.InvoiceDetail;
import com.ra.model.entity.ShoppingCart;
import com.ra.model.service.InvoiceDetailService;
import com.ra.model.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @GetMapping("/invoice")
    public String getAllInvoice(Model model){
        List<Invoice> invoices = invoiceService.findAll();
        model.addAttribute("invoices",invoices);
        return "admin/invoice/invoice";
    }

    @GetMapping("/invoiceDetail/{id}")
    public String invoiceDetail(Model model, @PathVariable Long id){
        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("invoice",invoice);
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllByInvoice(invoice);
        model.addAttribute("invoiceDetails",invoiceDetails);
        double totalPrice = (double) 0;
        for (InvoiceDetail invoiceDetail:invoiceDetails){
            totalPrice+=(invoiceDetail.getUnitPrice()*invoiceDetail.getInvoiceQuantity());
        }
        model.addAttribute("totalPrice",totalPrice);
        return "admin/invoice/invoice-details";
    }


}
