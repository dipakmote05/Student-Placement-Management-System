package service;

import dao.StudentDAO;
import model.Student;

import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    // CREATE
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // READ BY ID
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    // UPDATE
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    // DELETE
    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }
}