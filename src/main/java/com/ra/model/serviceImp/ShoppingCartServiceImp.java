package com.ra.model.serviceImp;

import com.ra.model.entity.*;
import com.ra.model.entity.Enum.StatusInvoice;
import com.ra.model.entity.dto.CheckOutInfor;
import com.ra.model.repository.InvoiceDetailRepository;
import com.ra.model.repository.InvoiceRepository;
import com.ra.model.repository.ShoppingCartRepository;
import com.ra.model.service.ProductService;
import com.ra.model.service.ShoppingCartService;
import com.ra.security.UserDetail.UserLogin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserLogin userLogin;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private ProductService productService;
    @Override
    public List<ShoppingCart> getAllByUser(User user) {
        return shoppingCartRepository.getAllByUser(user);
    }
    @Transactional
    @Override
    public void deleteByUserAndId(User user, Long id) {
        shoppingCartRepository.deleteShoppingCartByIdAndUser(id,user);
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void updateOrderQuantity(Long id, Integer quantity) {
        ShoppingCart cart = findById(id);
        cart.setOrderQuantity(quantity);
        save(cart);
    }

    @Override
    public void deleteByUser(User user) {
        shoppingCartRepository.deleteByUser(user);
    }
    @Transactional
    @Override
    public void checkOut(CheckOutInfor checkOutInfor) {
        User user = userLogin.userLogin();
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setCreatedAt(new Date(new java.util.Date().getTime()));
        LocalDateTime createdAt = LocalDateTime.ofInstant(invoice.getCreatedAt().toInstant(), ZoneId.systemDefault());
        LocalDateTime receivedAt = createdAt.plusDays(3);
        invoice.setReceivedAt(Date.from(receivedAt.atZone(ZoneId.systemDefault()).toInstant()));
        invoice.setReceiveName(checkOutInfor.getReceiveName());
        invoice.setReceivePhone(checkOutInfor.getReceivePhone());
        invoice.setReceiveAddress(checkOutInfor.getReceiveAddress());
        invoice.setNote(checkOutInfor.getNote());
        invoice.setStatusInvoice(StatusInvoice.WAITING);
        invoice.setSerialNumber(UUID.randomUUID().toString());
        double totalPriceMoney = 0;
        List<ShoppingCart> carts = shoppingCartRepository.getAllByUser(user);
        for (ShoppingCart cart:carts){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setName(cart.getProduct().getProductName());
            invoiceDetail.setInvoiceQuantity(cart.getOrderQuantity());
            invoiceDetail.setUnitPrice(cart.getProduct().getUnitPrice());
            invoiceDetail.setInvoice(invoice);
            invoiceDetail.setProduct(productService.findById(cart.getProduct().getId()));
            totalPriceMoney+=(cart.getOrderQuantity()*cart.getProduct().getUnitPrice());
            invoiceDetailRepository.save(invoiceDetail);
        }
        invoice.setTotalPrice(totalPriceMoney);
        invoiceRepository.save(invoice);
        deleteByUser(user);
    }


}
