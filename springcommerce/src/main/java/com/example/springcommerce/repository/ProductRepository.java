package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.springcommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByNameContaining(String keyword);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceBetween(int minPrice, int maxPrice);


}
