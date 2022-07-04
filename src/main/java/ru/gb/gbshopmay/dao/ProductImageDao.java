package ru.gb.gbshopmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gb.gbshopmay.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;


public interface ProductImageDao extends JpaRepository<ProductImage, Long> {

    @Query(value = "SELECT product_image.path FROM product_image WHERE product_image.product_id = :id LIMIT 1", nativeQuery = true)
    String findImageNameByProductId(@Param("id") Long id);

    @Query(value = "SELECT product_image.path FROM product_image WHERE product_image.product_id = :id", nativeQuery = true)
    ArrayList<String> findMultipleImageNamesByProductId(@Param("id") Long id);
}