package com.eltonkaqiu.resbackend.controllers;

import com.eltonkaqiu.resbackend.dtos.ReviewDto;
import com.eltonkaqiu.resbackend.models.Review;
import com.eltonkaqiu.resbackend.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/")
    public ResponseEntity<ReviewDto> createReview(@RequestBody @Valid Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody @Valid Review newReview, @PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.updateReview(newReview, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReviewDto>> getReviewByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
