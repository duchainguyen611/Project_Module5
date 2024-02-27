package com.ra.model.repository;

import com.ra.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query(value = "select od.* from order_detail od join orders o on od.order_id=o.id where o.id=?1",nativeQuery = true)
    List<OrderDetail> getAll(Long orderId);



}
