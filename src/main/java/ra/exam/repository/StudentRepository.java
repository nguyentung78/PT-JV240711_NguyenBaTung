package ra.exam.repository;

import ra.exam.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findById(int id);
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(int studentId);
    boolean existsByStudentName(String studentName);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}
