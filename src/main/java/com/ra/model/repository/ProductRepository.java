package com.ra.model.repository;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
        @Query(value = "select * from product",nativeQuery = true)
        Page<Product> getAllProductHome(Pageable pageable);
        List<Product> getAllByCategory(Category category);
        List<Product> getAllByVendor(Vendor vendor);
        List<Product> findAllByProductNameContainingIgnoreCase(String productName);

}
