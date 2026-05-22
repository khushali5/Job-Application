package com.embarkx.jobMS.JOB.clients;

import com.embarkx.jobMS.JOB.external.Company;
import com.embarkx.jobMS.JOB.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEW-SERVICE",url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);

}
