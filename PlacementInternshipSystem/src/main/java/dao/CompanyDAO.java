package dao;

import model.Company;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class CompanyDAO {

    // CREATE
    public void addCompany(Company company) {

        String sql = "INSERT INTO companies " +
                "(company_name, email, location, website) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getLocation());
            statement.setString(4, company.getWebsite());

            statement.executeUpdate();

            System.out.println("Company added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding company.");
            e.printStackTrace();
        }
    }
    // READ ALL
    public List<Company> getAllCompanies() {

        List<Company> companies = new ArrayList<>();

        String sql = "SELECT * FROM companies";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Company company = new Company();

                company.setCompanyId(resultSet.getInt("company_id"));
                company.setCompanyName(resultSet.getString("company_name"));
                company.setEmail(resultSet.getString("email"));
                company.setLocation(resultSet.getString("location"));
                company.setWebsite(resultSet.getString("website"));

                companies.add(company);
            }

        } catch (SQLException e) {

            System.out.println("Error getting companies.");
            e.printStackTrace();
        }

        return companies;
    }
    // READ ONE
    public Company getCompanyById(int companyId) {

        String sql = "SELECT * FROM companies WHERE company_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, companyId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Company company = new Company();

                    company.setCompanyId(resultSet.getInt("company_id"));
                    company.setCompanyName(resultSet.getString("company_name"));
                    company.setEmail(resultSet.getString("email"));
                    company.setLocation(resultSet.getString("location"));
                    company.setWebsite(resultSet.getString("website"));

                    return company;
                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting company.");
            e.printStackTrace();
        }

        return null;
    }
    // UPDATE
    public void updateCompany(Company company) {

        String sql = "UPDATE companies SET company_name = ?, email = ?, " +
                "location = ?, website = ? " +
                "WHERE company_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getLocation());
            statement.setString(4, company.getWebsite());
            statement.setInt(5, company.getCompanyId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Company updated successfully!");
            } else {
                System.out.println("Company not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating company.");
            e.printStackTrace();
        }
    }
    // DELETE
    public void deleteCompany(int companyId) {

        String sql = "DELETE FROM companies WHERE company_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, companyId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Company deleted successfully!");
            } else {
                System.out.println("Company not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting company.");
            e.printStackTrace();
        }
    }
}