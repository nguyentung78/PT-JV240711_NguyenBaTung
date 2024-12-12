package ra.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.exam.model.Clazz;
import ra.exam.model.Student;
import ra.exam.service.ClazzService;
import ra.exam.service.StudentService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/studentsController")
public class StudentsController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Clazz> listClasses = clazzService.findAll();
        List<Student> listStudents = studentService.findAll();
        model.addAttribute("listClasses", listClasses);
        model.addAttribute("listStudents", listStudents);
        return "listStudents";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        List<Clazz> listClasses = clazzService.findAll();
        model.addAttribute("listClasses", listClasses);
        model.addAttribute("student", new Student());
        return "createStudent";
    }

    @PostMapping("/create")
    public String create(@Valid Student student,
                         BindingResult bindingResult,
                         @RequestParam("avatarFile") MultipartFile avatarFile,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listClasses", clazzService.findAll());
            return "createStudent";
        }

        try {
            boolean result = studentService.save(student, avatarFile);
            if (result) {
                return "redirect:/studentsController/findAll";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("listClasses", clazzService.findAll());
            return "createStudent";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error uploading file.");
            model.addAttribute("listClasses", clazzService.findAll());
            return "createStudent";
        }

        return "error";
    }

    @GetMapping("/initUpdate")
    public String initUpdate(Model model, @RequestParam(name = "id", required = true) Integer studentId) {
        List<Clazz> listClasses = clazzService.findAll();
        model.addAttribute("listClasses", listClasses);
        if (studentId == null) {
            return "error";
        }
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/update")
    public String update(@Valid Student student,
                         BindingResult bindingResult,
                         @RequestParam(name = "avatarFile", required = false) MultipartFile avatarFile,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listClasses", clazzService.findAll());
            return "updateStudent";
        }
        try {
            // Kiểm tra xem file có được upload hay không
            if (avatarFile != null && !avatarFile.isEmpty()) {
                studentService.update(student, avatarFile);
            } else {
                studentService.updateWithoutAvatar(student);
            }
            return "redirect:/studentsController/findAll";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error uploading file.");
            model.addAttribute("listClasses", clazzService.findAll());
            return "updateStudent";
        }
    }


    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id", required = true) Integer studentId) {
        boolean result = studentService.delete(studentId);
        if (result) {
            return "redirect:/studentsController/findAll";
        }
        return "error";
    }
}
