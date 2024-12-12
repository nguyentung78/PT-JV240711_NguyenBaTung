package ra.exam.service;

import org.springframework.web.multipart.MultipartFile;
import ra.exam.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    boolean save(Student student, MultipartFile avatarFile) throws IOException;
    boolean update(Student student, MultipartFile avatarFile) throws IOException;
    boolean delete(int studentId);
    boolean updateWithoutAvatar(Student student);
}
