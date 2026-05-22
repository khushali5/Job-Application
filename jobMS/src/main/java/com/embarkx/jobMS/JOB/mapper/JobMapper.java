package com.embarkx.jobMS.JOB.mapper;

import com.embarkx.jobMS.JOB.DTO.JobDTO;
import com.embarkx.jobMS.JOB.Job;
import com.embarkx.jobMS.JOB.external.Company;
import com.embarkx.jobMS.JOB.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(
            Job job,
            Company company, List<Review> reviews)
    {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReview(reviews);
        return jobDTO;
    }
}
