package com.embarkx.CompanyMS;

import com.embarkx.CompanyMS.dto.ReviewMessage;

import java.util.List;

public interface CompanyService
{
     List<Company> getAllCompanies();
     boolean updateCompany(Company company,Long id);

     void createCompany(Company company);
     boolean deleteCompany(Long id);
     Company getCompanybyID(Long id);

     void refreshCompanyRating(Long companyId);
}
