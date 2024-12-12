package ra.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.exam.dto.request.StudentRequestDto;
import ra.exam.model.Student;
import ra.exam.service.StudentService;
import ra.exam.service.ClazzService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/findAll")
    public String findAllStudents(Model model) {
        List<Student> listStudents = studentService.findAll();
        model.addAttribute("listStudents", listStudents);
        return "students";
    }

    @GetMapping("/initCreate")
    public String initCreateStudent(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        model.addAttribute("classes", clazzService.findAll());
        return "newStudent";
    }

    @PostMapping("/create")
    public String createStudent(
            @Valid @ModelAttribute("student") StudentRequestDto studentDto,
            BindingResult result,
            Model model
    ) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("classes", clazzService.findAll());
            return "newStudent";
        }

        try {
            boolean saved = studentService.save(studentDto);
            if (saved) {
                return "redirect:/studentController/findAll";
            }
        } catch (IllegalArgumentException e) {
            result.rejectValue("classId", "error.class", e.getMessage());
        }

        model.addAttribute("classes", clazzService.findAll());
        return "newStudent";
    }


    @GetMapping("/initUpdate")
    public String initUpdateStudent(@RequestParam("studentId") int studentId, Model model) {
        Student existingStudent = studentService.findById(studentId);
        if (existingStudent == null) {
            model.addAttribute("error", "Student not found.");
            return "error";
        }

        StudentRequestDto studentDto = new StudentRequestDto();
        studentDto.setStudentId(String.valueOf(existingStudent.getStudentId()));
        studentDto.setStudentName(existingStudent.getStudentName());
        studentDto.setPhoneNumber(existingStudent.getPhoneNumber());
        studentDto.setEmail(existingStudent.getEmail());
        studentDto.setAddress(existingStudent.getAddress());
        studentDto.setSex(existingStudent.isSex());
        studentDto.setClassId(existingStudent.getStudentClass().getClassId());
        studentDto.setStatus(existingStudent.getStatus());

        model.addAttribute("student", studentDto);
        model.addAttribute("classes", clazzService.findAll());
        return "updateStudent";
    }

    @PostMapping("/update")
    public String updateStudent(
            @Valid @ModelAttribute("student") StudentRequestDto studentDto,
            BindingResult result,
            Model model
    ) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("classes", clazzService.findAll());
            return "updateStudent";
        }

        boolean updated = studentService.update(studentDto);
        if (updated) {
            return "redirect:/studentController/findAll";
        }
        model.addAttribute("error", "Failed to update student.");
        return "error";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model) {
        try {
            studentService.delete(studentId);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "students"; // Return to the list of students
        }
        return "redirect:/studentController/findAll";
    }
}