package com.embarkx.CompanyMS;
import com.embarkx.CompanyMS.dto.ReviewMessage;
import com.embarkx.CompanyMS.clients.ReviewClient;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repo;
    private final ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository repo, ReviewClient reviewClient) {
        this.repo = repo;
        this.reviewClient = reviewClient;
    }

    // ---------------- GET ALL ----------------
    @Override
    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    // ---------------- GET BY ID ----------------
    @Override
    public Company getCompanybyID(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ---------------- CREATE COMPANY ----------------
    @Override
    public void createCompany(Company company) {
        company.setRating(0.0); // 🔥 IMPORTANT DEFAULT VALUE
        repo.save(company);
    }

    // ---------------- DELETE ----------------
    @Override
    public boolean deleteCompany(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // ---------------- UPDATE COMPANY ----------------
    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> optional = repo.findById(id);

        if (optional.isPresent()) {
            Company existing = optional.get();

            existing.setName(company.getName());
            existing.setDescription(company.getDescription());

            repo.save(existing);
            return true;
        }
        return false;
    }

    // ---------------- 🔥 FIX: RATING UPDATE ----------------
    @Override
    public void refreshCompanyRating(Long companyId) {

        Company company = repo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));

        List<ReviewMessage> reviews = reviewClient.getReviews(companyId);

        double avg = reviews.stream()
                .mapToDouble(ReviewMessage::getRating)
                .average()
                .orElse(0.0);

        company.setRating(avg);

        repo.save(company);
    }
}