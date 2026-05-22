package com.embarkx.CompanyMS.Messaging;

import com.embarkx.CompanyMS.CompanyService;
import com.embarkx.CompanyMS.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {

        System.out.println("MESSAGE RECEIVED");
        System.out.println("Company ID: " + reviewMessage.getCompanyId());

        companyService.refreshCompanyRating(
                reviewMessage.getCompanyId()
        );
    }
}