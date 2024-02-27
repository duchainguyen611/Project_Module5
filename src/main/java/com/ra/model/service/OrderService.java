package com.ra.model.service;

import com.ra.model.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getAll();
    Boolean add(Orders orders);
    Boolean update(Orders orders);
    Boolean delete(Long id);
    Orders findById(Long id);
    void approve(Long id);
    void detailOrder(Long id);
}
