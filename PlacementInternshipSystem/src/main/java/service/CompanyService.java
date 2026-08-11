package service;

import dao.CompanyDAO;
import model.Company;

import java.util.List;

public class CompanyService {

    private CompanyDAO companyDAO;

    public CompanyService() {
        companyDAO = new CompanyDAO();
    }

    // CREATE
    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    // READ ALL
    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    // READ BY ID
    public Company getCompanyById(int companyId) {
        return companyDAO.getCompanyById(companyId);
    }

    // UPDATE
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    // DELETE
    public void deleteCompany(int companyId) {
        companyDAO.deleteCompany(companyId);
    }
}