package dao;

import model.Opportunity;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpportunityDAO {

    // =========================
    // CREATE
    // =========================

    public void addOpportunity(Opportunity opportunity) {

        String sql = "INSERT INTO opportunities " +
                "(company_id, title, type, description, required_skill, minimum_cgpa, location, salary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, opportunity.getCompanyId());
            statement.setString(2, opportunity.getTitle());
            statement.setString(3, opportunity.getType());
            statement.setString(4, opportunity.getDescription());
            statement.setString(5, opportunity.getRequiredSkill());
            statement.setDouble(6, opportunity.getMinimumCgpa());
            statement.setString(7, opportunity.getLocation());
            statement.setDouble(8, opportunity.getSalary());

            statement.executeUpdate();

            System.out.println("Opportunity added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding opportunity.");
            e.printStackTrace();
        }
    }


    // =========================
    // READ ALL
    // =========================

    public List<Opportunity> getAllOpportunities() {

        List<Opportunity> opportunities = new ArrayList<>();

        String sql = "SELECT * FROM opportunities";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Opportunity opportunity = new Opportunity();

                opportunity.setOpportunityId(
                        resultSet.getInt("opportunity_id")
                );

                opportunity.setCompanyId(
                        resultSet.getInt("company_id")
                );

                opportunity.setTitle(
                        resultSet.getString("title")
                );

                opportunity.setType(
                        resultSet.getString("type")
                );

                opportunity.setDescription(
                        resultSet.getString("description")
                );

                opportunity.setRequiredSkill(
                        resultSet.getString("required_skill")
                );

                opportunity.setMinimumCgpa(
                        resultSet.getDouble("minimum_cgpa")
                );

                opportunity.setLocation(
                        resultSet.getString("location")
                );

                opportunity.setSalary(
                        resultSet.getDouble("salary")
                );

                opportunities.add(opportunity);
            }

        } catch (SQLException e) {

            System.out.println("Error getting opportunities.");
            e.printStackTrace();
        }

        return opportunities;
    }


    // =========================
    // READ BY ID
    // =========================

    public Opportunity getOpportunityById(int opportunityId) {

        String sql =
                "SELECT * FROM opportunities WHERE opportunity_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, opportunityId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Opportunity opportunity = new Opportunity();

                    opportunity.setOpportunityId(
                            resultSet.getInt("opportunity_id")
                    );

                    opportunity.setCompanyId(
                            resultSet.getInt("company_id")
                    );

                    opportunity.setTitle(
                            resultSet.getString("title")
                    );

                    opportunity.setType(
                            resultSet.getString("type")
                    );

                    opportunity.setDescription(
                            resultSet.getString("description")
                    );

                    opportunity.setRequiredSkill(
                            resultSet.getString("required_skill")
                    );

                    opportunity.setMinimumCgpa(
                            resultSet.getDouble("minimum_cgpa")
                    );

                    opportunity.setLocation(
                            resultSet.getString("location")
                    );

                    opportunity.setSalary(
                            resultSet.getDouble("salary")
                    );

                    return opportunity;
                }
            }

        } catch (SQLException e) {

            System.out.println("Error getting opportunity.");
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // UPDATE
    // =========================

    public void updateOpportunity(Opportunity opportunity) {

        String sql =
                "UPDATE opportunities SET " +
                        "company_id = ?, " +
                        "title = ?, " +
                        "type = ?, " +
                        "description = ?, " +
                        "required_skill = ?, " +
                        "minimum_cgpa = ?, " +
                        "location = ?, " +
                        "salary = ? " +
                        "WHERE opportunity_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, opportunity.getCompanyId());
            statement.setString(2, opportunity.getTitle());
            statement.setString(3, opportunity.getType());
            statement.setString(4, opportunity.getDescription());
            statement.setString(5, opportunity.getRequiredSkill());
            statement.setDouble(6, opportunity.getMinimumCgpa());
            statement.setString(7, opportunity.getLocation());
            statement.setDouble(8, opportunity.getSalary());

            statement.setInt(
                    9,
                    opportunity.getOpportunityId()
            );

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Opportunity updated successfully!");
            } else {
                System.out.println("Opportunity not found.");
            }

        } catch (SQLException e) {

            System.out.println("Error updating opportunity.");
            e.printStackTrace();
        }
    }


    // =========================
    // DELETE
    // =========================

    public void deleteOpportunity(int opportunityId) {

        String sql =
                "DELETE FROM opportunities WHERE opportunity_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, opportunityId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Opportunity deleted successfully!");
            } else {
                System.out.println("Opportunity not found.");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting opportunity.");
            e.printStackTrace();
        }
    }
}