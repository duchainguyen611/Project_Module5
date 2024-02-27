package com.ra.model.service;

import com.ra.model.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetail> getAll(Long orderId);
    void add(OrderDetail orderDetail,Long orderId);
    void update(OrderDetail orderDetail);
    void delete(Long id);
    OrderDetail findById(Long id);
}
