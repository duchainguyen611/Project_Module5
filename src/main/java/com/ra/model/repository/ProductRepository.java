package com.ra.model.repository;

import com.ra.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p.* from product p join order_detail od on p.id=od.product_id join orders o on od.order_id=o.id where o.id=?1",nativeQuery = true)
    List<Product> getAllProductInOrder(Long orderId);
}
