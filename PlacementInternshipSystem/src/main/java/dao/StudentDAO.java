package dao;

import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE
    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(name, email, phone, degree, branch, cgpa, graduation_year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getDegree());
            statement.setString(5, student.getBranch());
            statement.setDouble(6, student.getCgpa());
            statement.setInt(7, student.getGraduationYear());

            statement.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding student.");
            e.printStackTrace();
        }
    }
    // READ - Get all students
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student();

                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setDegree(resultSet.getString("degree"));
                student.setBranch(resultSet.getString("branch"));
                student.setCgpa(resultSet.getDouble("cgpa"));
                student.setGraduationYear(resultSet.getInt("graduation_year"));

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching students.");
            e.printStackTrace();
        }

        return students;
    }
    // READ - Get student by ID
    public Student getStudentById(int studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Student student = new Student();

                    student.setStudentId(resultSet.getInt("student_id"));
                    student.setName(resultSet.getString("name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setDegree(resultSet.getString("degree"));
                    student.setBranch(resultSet.getString("branch"));
                    student.setCgpa(resultSet.getDouble("cgpa"));
                    student.setGraduationYear(resultSet.getInt("graduation_year"));

                    return student;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching student.");
            e.printStackTrace();
        }

        return null;
    }
    // UPDATE
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET name = ?, email = ?, phone = ?, " +
                "degree = ?, branch = ?, cgpa = ?, graduation_year = ? " +
                "WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getDegree());
            statement.setString(5, student.getBranch());
            statement.setDouble(6, student.getCgpa());
            statement.setInt(7, student.getGraduationYear());
            statement.setInt(8, student.getStudentId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating student.");
            e.printStackTrace();
        }
    }
    // DELETE
    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting student.");
            e.printStackTrace();
        }
    }

}