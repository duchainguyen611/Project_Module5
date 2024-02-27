package com.ra.model.serviceImp;

import com.ra.model.entity.OrderDetail;
import com.ra.model.entity.Orders;
import com.ra.model.repository.OrderDetailRepository;
import com.ra.model.repository.OrderRepository;
import com.ra.model.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<OrderDetail> getAll(Long orderId) {
        return orderDetailRepository.getAll(orderId);
    }

    @Override
    public void add(OrderDetail orderDetail,Long orderId) {
        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        if (ordersOptional.isPresent()){
            Orders orders = ordersOptional.get();
            orderDetail.setOrders(orders);
            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }
}
