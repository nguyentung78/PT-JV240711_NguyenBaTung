package ra.exam.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.exam.model.Student;
import ra.exam.repository.StudentRepository;
import ra.exam.service.StudentService;
import ra.exam.service.UploadFileService;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean save(Student student, MultipartFile avatarFile) throws IOException {
        if (studentRepository.existsByStudentName(student.getStudentName())) {
            throw new IllegalArgumentException("Tên sinh viên đã tồn tại.");
        }
        if (studentRepository.existsByPhoneNumber(student.getPhoneNumber())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại.");
        }
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }

        if (avatarFile != null && !avatarFile.isEmpty()) {
            String imageUrl = uploadFileService.uploadFile(avatarFile);
            student.setImageUrl(imageUrl);
        }

        return studentRepository.save(student);
    }

    @Override
    public boolean update(Student student, MultipartFile avatarFile) throws IOException {
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String imageUrl = uploadFileService.uploadFile(avatarFile);
            student.setImageUrl(imageUrl);
        } else {
            Student existingStudent = studentRepository.findById(student.getStudentId());
            if (existingStudent != null) {
                student.setImageUrl(existingStudent.getImageUrl());
            }
        }
        return studentRepository.update(student);
    }


    @Override
    public boolean delete(int studentId) {
        return studentRepository.delete(studentId);
    }

    @Override
    public boolean updateWithoutAvatar(Student student) {
        Student existingStudent = studentRepository.findById(student.getStudentId());
        if (existingStudent != null) {
            student.setImageUrl(existingStudent.getImageUrl());
            return studentRepository.update(student);
        }
        return false;
    }

}
