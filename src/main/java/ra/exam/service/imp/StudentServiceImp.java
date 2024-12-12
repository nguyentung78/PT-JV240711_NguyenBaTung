package ra.exam.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exam.dto.request.StudentRequestDto;
import ra.exam.model.Clazz;
import ra.exam.model.Student;
import ra.exam.repository.StudentRepository;
import ra.exam.service.ClazzService;
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

    @Autowired
    private ClazzService clazzService;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public boolean save(StudentRequestDto studentDto) throws IOException {
        try {
            String imageUrl = null;
            if (studentDto.getImage() != null && !studentDto.getImage().isEmpty()) {
                imageUrl = uploadFileService.uploadFile(studentDto.getImage());
            }

            // Kiểm tra Clazz tồn tại
            Clazz clazz = clazzService.findById(studentDto.getClassId());
            if (clazz == null) {
                throw new IllegalArgumentException("Invalid Class ID: " + studentDto.getClassId());
            }

            Student student = new Student(
                    0, // Auto-generated ID
                    studentDto.getStudentName(),
                    studentDto.getPhoneNumber(),
                    studentDto.getEmail(),
                    studentDto.getAddress(),
                    studentDto.getSex(),
                    clazz,
                    imageUrl,
                    studentDto.getStatus()
            );

            studentRepository.save(student);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(StudentRequestDto studentDto) throws IOException {
        try {
            Student existingStudent = studentRepository.findById(Integer.parseInt(studentDto.getStudentId()));
            if (existingStudent == null) {
                return false;
            }

            // Kiểm tra Clazz tồn tại
            Clazz clazz = clazzService.findById(studentDto.getClassId());
            if (clazz == null) {
                throw new IllegalArgumentException("Invalid Class ID: " + studentDto.getClassId());
            }

            existingStudent.setStudentName(studentDto.getStudentName());
            existingStudent.setPhoneNumber(studentDto.getPhoneNumber());
            existingStudent.setEmail(studentDto.getEmail());
            existingStudent.setAddress(studentDto.getAddress());
            existingStudent.setSex(studentDto.getSex());
            existingStudent.setStudentClass(clazz);
            existingStudent.setStatus(studentDto.getStatus());

            // Kiểm tra nếu có avatar mới
            if (studentDto.getImage() != null && !studentDto.getImage().isEmpty()) {
                String imageUrl = uploadFileService.uploadFile(studentDto.getImage());
                existingStudent.setImageUrl(imageUrl);
            }

            studentRepository.update(existingStudent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int studentId) {
        try {
            return studentRepository.delete(studentId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Student> findByClassId(int classId) {
        return studentRepository.findByClassId(classId);
    }
}