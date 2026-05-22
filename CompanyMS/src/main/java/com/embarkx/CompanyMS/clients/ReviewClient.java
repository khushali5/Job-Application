package com.embarkx.CompanyMS.clients;

import com.embarkx.CompanyMS.dto.ReviewMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "review-service", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<ReviewMessage> getReviews(@RequestParam("companyId") Long companyId);
}