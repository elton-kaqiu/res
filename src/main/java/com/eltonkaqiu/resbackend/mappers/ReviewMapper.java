package com.eltonkaqiu.resbackend.mappers;

import com.eltonkaqiu.resbackend.dtos.ReviewDto;
import com.eltonkaqiu.resbackend.models.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final ModelMapper modelMapper;

    public Review toEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, Review.class);
    }

    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .fullName(String.format("%s %s", review.getUser().getFirstName(), review.getUser().getLastName()))
                .build();
    }
}
