package com.embarkx.jobMS.JOB;

import com.embarkx.jobMS.JOB.DTO.JobDTO;
import com.embarkx.jobMS.JOB.clients.CompanyClient;
import com.embarkx.jobMS.JOB.clients.ReviewClient;
import com.embarkx.jobMS.JOB.external.Company;
import com.embarkx.jobMS.JOB.external.Review;
import com.embarkx.jobMS.JOB.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepo repo;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobServiceImpl(JobRepo repo,
                          CompanyClient companyClient,
                          ReviewClient reviewClient) {
        this.repo = repo;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    @RateLimiter(name = "companyBreaker", fallbackMethod = "fallbackFindAll")
    public List<JobDTO> findall() {

        List<Job> jobs = repo.findAll();

        return jobs.stream()
                .map(this::converttoDto)
                .collect(Collectors.toList());
    }

    // ✅ FIXED FALLBACK (MATCH RETURN TYPE)
    public List<JobDTO> fallbackFindAll(Exception e) {
        return new ArrayList<>();
    }

    private JobDTO converttoDto(Job job) {

        Company company = null;
        List<Review> reviews = new ArrayList<>();

        try {
            company = companyClient.getCompany(job.getCompanyId());
        } catch (Exception e) {
            System.out.println("Company service failed: " + e.getMessage());
        }

        try {
            reviews = reviewClient.getReviews(job.getCompanyId());
        } catch (Exception e) {
            System.out.println("Review service failed: " + e.getMessage());
        }

        return JobMapper.mapToJobWithCompanyDto(job, company, reviews);
    }

    @Override
    public JobDTO getjobbyID(Long id) {
        return repo.findById(id)
                .map(this::converttoDto)
                .orElse(null);
    }

    @Override
    public void createJob(Job job) {
        repo.save(job);
    }

    @Override
    public Boolean deletejobbyID(Long id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public boolean updatejob(Long id, Job updatedJob) {

        return repo.findById(id).map(job -> {
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            repo.save(job);
            return true;
        }).orElse(false);
    }
}