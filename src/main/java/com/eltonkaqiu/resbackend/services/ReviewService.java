package com.eltonkaqiu.resbackend.services;

import com.eltonkaqiu.resbackend.dtos.ReviewDto;
import com.eltonkaqiu.resbackend.models.Review;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(Review review);

    ReviewDto updateReview(Review newReview, Integer id);

    ReviewDto getReviewById(Integer id);

    List<ReviewDto> getAllReviews();

    List<ReviewDto> getReviewsByUserId(Integer userId);

    void deleteReviewById(Integer id);
}
