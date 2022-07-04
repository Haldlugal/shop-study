package ru.gb.gbshopmay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gbapimay.review.dto.ReviewDto;
import ru.gb.gbshopmay.dao.ReviewDao;
import ru.gb.gbshopmay.entity.Review;
import ru.gb.gbshopmay.web.dto.mapper.ReviewMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;

    public Review save(Review review) {
        return reviewDao.save(review);
    }

    public List<ReviewDto> findReviewsByProductId(Long id) {
        ArrayList<Review> reviews =  reviewDao.findAllByProductId(id);
        return reviews.stream().map(reviewMapper::toReviewDto).collect(Collectors.toList());
    }
}
