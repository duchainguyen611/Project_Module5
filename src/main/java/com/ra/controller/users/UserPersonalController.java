package com.ra.controller.users;

import com.ra.model.entity.Invoice;
import com.ra.model.entity.InvoiceDetail;
import com.ra.model.entity.User;
import com.ra.model.service.InvoiceDetailService;
import com.ra.model.service.InvoiceService;
import com.ra.model.service.UserService;
import com.ra.security.UserDetail.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPersonalController {
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogin userLogin;
    @GetMapping("/profile")
    public String profileUser(Model model){
        User user = userLogin.userLogin();
        List<Invoice> invoiceList = invoiceService.findAllByUser(user);
        model.addAttribute("invoiceList",invoiceList);
        return "home/profile/my-account";
    }

    @GetMapping("/invoiceDetail/{id}")
    public String invoiceDetail(Model model, @PathVariable Long id){
        User user = userLogin.userLogin();
        Invoice invoice = invoiceService.findById(id);
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllByInvoice(invoice);
        model.addAttribute("user",user);
        model.addAttribute("invoice",invoice);
        model.addAttribute("invoiceDetails",invoiceDetails);
        return "home/invoice/invoice";
    }

    @GetMapping("")
    public String home() {
        return "home/index";
    }

}
