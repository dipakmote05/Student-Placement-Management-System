package dao;

import model.Application;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // CREATE
    public void addApplication(Application application) {

        String sql = "INSERT INTO applications " +
                "(student_id, opportunity_id, application_date, status) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, application.getStudentId());
            statement.setInt(2, application.getOpportunityId());
            statement.setDate(3,
                    java.sql.Date.valueOf(application.getApplicationDate()));
            statement.setString(4, application.getStatus());

            statement.executeUpdate();

            System.out.println("Application added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding application.");
            e.printStackTrace();
        }
    }
    // READ ALL
    public List<Application> getAllApplications() {

        List<Application> applications = new ArrayList<>();

        String sql = "SELECT * FROM applications";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Application application = new Application();

                application.setApplicationId(
                        resultSet.getInt("application_id"));

                application.setStudentId(
                        resultSet.getInt("student_id"));

                application.setOpportunityId(
                        resultSet.getInt("opportunity_id"));

                application.setApplicationDate(
                        resultSet.getDate("application_date").toLocalDate());

                application.setStatus(
                        resultSet.getString("status"));

                applications.add(application);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching applications.");
            e.printStackTrace();
        }

        return applications;
    }
    // READ BY ID
    public Application getApplicationById(int applicationId) {

        String sql = "SELECT * FROM applications WHERE application_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, applicationId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Application application = new Application();

                application.setApplicationId(
                        resultSet.getInt("application_id"));

                application.setStudentId(
                        resultSet.getInt("student_id"));

                application.setOpportunityId(
                        resultSet.getInt("opportunity_id"));

                application.setApplicationDate(
                        resultSet.getDate("application_date").toLocalDate());

                application.setStatus(
                        resultSet.getString("status"));

                return application;
            }

        } catch (SQLException e) {

            System.out.println("Error getting application.");
            e.printStackTrace();
        }

        return null;
    }
    // UPDATE
    public void updateApplication(Application application) {

        String sql = "UPDATE applications SET status = ? WHERE application_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, application.getStatus());
            statement.setInt(2, application.getApplicationId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Application updated successfully!");
            } else {
                System.out.println("Application not found.");
            }

        } catch (SQLException e) {

            System.out.println("Error updating application.");
            e.printStackTrace();
        }
    }
    // DELETE
    public void deleteApplication(int applicationId) {

        String sql = "DELETE FROM applications WHERE application_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, applicationId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Application deleted successfully!");
            } else {
                System.out.println("Application not found.");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting application.");
            e.printStackTrace();
        }

    }
    // CHECK DUPLICATE APPLICATION
    public boolean hasAlreadyApplied(int studentId, int opportunityId) {

        String sql =
                "SELECT COUNT(*) FROM applications " +
                        "WHERE student_id = ? AND opportunity_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, opportunityId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error checking existing application.");

            e.printStackTrace();
        }

        return false;
    }
}