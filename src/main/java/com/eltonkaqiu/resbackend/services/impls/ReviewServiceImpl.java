package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.ReviewDto;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.mappers.ReviewMapper;
import com.eltonkaqiu.resbackend.models.Review;
import com.eltonkaqiu.resbackend.models.User;
import com.eltonkaqiu.resbackend.repositories.ReviewRepository;
import com.eltonkaqiu.resbackend.repositories.UserRepository;
import com.eltonkaqiu.resbackend.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto addReview(Review review) {
        Integer userId = review.getUser().getId();
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " not found")
        );
        review.setUser(existingUser);
        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    @Override
    public ReviewDto updateReview(Review newReview, Integer id) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id + " not found")
        );
        updateReviewDetails(existingReview, newReview, id);
        var updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.toDto(updatedReview);
    }

    @Override
    public ReviewDto getReviewById(Integer id) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id + " not found")
        );
        return reviewMapper.toDto(existingReview);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(Integer userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteReviewById(Integer id) {
        this.getReviewById(id);
        reviewRepository.deleteById(id);
    }

    private void updateReviewDetails(Review existingReview, Review newReview, Integer id) {
        existingReview.setId(id);
        existingReview.setRating(newReview.getRating());
        existingReview.setComment(newReview.getComment());
        existingReview.setCreatedAt(existingReview.getCreatedAt());
        existingReview.setUpdatedAt(LocalDateTime.now());
    }
}
