package com.embarkx.CompanyMS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    // ---------------- GET ALL ----------------
    @GetMapping
    public List<Company> getAllcompanies() {
        return service.getAllCompanies();
    }

    // ---------------- GET BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = service.getCompanybyID(id);
        return company != null
                ? new ResponseEntity<>(company, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        service.createCompany(company);
        return ResponseEntity.ok("Company added successfully");
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company) {
        service.updateCompany(company, id);
        return ResponseEntity.ok("Company updated successfully");
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        return service.deleteCompany(id)
                ? ResponseEntity.ok("Deleted successfully")
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    // ---------------- 🔥 NEW: RATING UPDATE ENDPOINT ----------------
    @PutMapping("/rating/{id}")
    public ResponseEntity<String> updateRating(@PathVariable Long id) {
        service.refreshCompanyRating(id);
        return ResponseEntity.ok("Rating updated successfully");
    }
}