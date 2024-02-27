package com.ra.model.serviceImp;

import com.ra.model.entity.OrderDetail;
import com.ra.model.entity.Orders;
import com.ra.model.repository.OrderDetailRepository;
import com.ra.model.repository.OrderRepository;
import com.ra.model.service.OrderDetailService;
import com.ra.model.service.OrderService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductService productService;

    @Override
    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Boolean add(Orders orders) {
        try {
            double totalPrice = 0;
            List<OrderDetail> orderDetails = orderDetailService.getAll(orders.getId());
            for (OrderDetail orderDetail : orderDetails) {
                totalPrice += orderDetail.getUnitPrice();
            }
            orders.setTotalPrice(totalPrice);
            orders.setSerialNumber(UUID.randomUUID().toString());
            orders.setStatusOrders(false);
            orderRepository.save(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Orders orders) {
        try {
            orderRepository.save(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void approve(Long id) {
        Orders orders = findById(id);
        if (orders!=null){
            orders.setStatusOrders(!orders.getStatusOrders());
            orderRepository.save(orders);
        }
    }
    @Override
    public void detailOrder(Long id) {
    }
}
