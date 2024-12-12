package ra.exam.service;

import ra.exam.dto.request.StudentRequestDto;
import ra.exam.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int studentId);
    boolean save(StudentRequestDto studentDto) throws IOException;
    boolean update(StudentRequestDto studentDto) throws IOException;
    boolean delete(int studentId);
    List<Student> findByClassId(int classId);
}
