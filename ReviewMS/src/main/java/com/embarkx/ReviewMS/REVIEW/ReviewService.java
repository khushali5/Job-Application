package com.embarkx.ReviewMS.REVIEW;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyID);
    boolean addReview(Long companyID,Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId ,Review review);
    boolean deleteReview(Long reviewId);
}
