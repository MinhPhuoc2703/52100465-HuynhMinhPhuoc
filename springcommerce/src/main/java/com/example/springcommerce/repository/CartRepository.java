package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springcommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    @Query(value = "DELETE FROM carts_products WHERE product_id = :productId", nativeQuery = true)
    void removeProductFromCart(@Param("productId") Long productId);


}
