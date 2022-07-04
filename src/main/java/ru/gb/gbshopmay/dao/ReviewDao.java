package ru.gb.gbshopmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gb.gbshopmay.entity.Review;

import java.util.ArrayList;

public interface ReviewDao extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM review WHERE review.product_id = :id", nativeQuery = true)
    ArrayList<Review> findAllByProductId(@Param("id") Long id);
}
