package com.embarkx.ReviewMS.REVIEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo repo;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = repo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review)
    {
           if(companyId!=null)
           {
               review.setCompanyId(companyId);
               repo.save(review);
               return true;
           }
           else
           {
               return false;
           }
    }

    @Override
    public Review getReview(Long reviewId) {
        return repo.findById(reviewId).orElse(null);
    }
    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review = repo.findById(reviewId).orElse(null);
        if(review != null)
        {
             review.setTitle(updatedreview.getTitle());
             review.setDescription(updatedreview.getDescription());
             review.setRating(updatedreview.getRating());
             review.setCompanyId(updatedreview.getCompanyId());
             repo.save(updatedreview);
             return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = repo.findById(reviewId).orElse(null);
            if (review != null)
            {
                repo.deleteById(reviewId);
                return true;
            }
            else
            {
                return false;
            }
        }

}
