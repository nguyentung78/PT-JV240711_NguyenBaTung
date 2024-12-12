package ra.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.exam.model.Clazz;
import ra.exam.service.ClazzService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/findAll")
    public String findAllClasses(Model model) {
        List<Clazz> listClasses = clazzService.findAll();
        model.addAttribute("listClasses", listClasses);
        return "clazzes";
    }

    @GetMapping("/initCreate")
    public String initCreateClazz(Model model) {
        model.addAttribute("clazz", new Clazz());
        return "newClazz";
    }

    @PostMapping("/create")
    public String createClazz(
            @Valid @ModelAttribute("clazz") Clazz clazz,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "newClazz";
        }
        boolean saved = clazzService.save(clazz);
        if (saved) {
            return "redirect:/clazzController/findAll";
        }
        model.addAttribute("error", "Failed to create class.");
        return "error";
    }

    @GetMapping("/initUpdate")
    public String initUpdateClazz(@RequestParam("classId") int classId, Model model) {
        Clazz clazz = clazzService.findById(classId);
        if (clazz == null) {
            model.addAttribute("error", "Class not found.");
            return "error";
        }
        model.addAttribute("clazz", clazz);
        return "updateClazz";
    }

    @PostMapping("/update")
    public String updateClazz(
            @Valid @ModelAttribute("clazz") Clazz clazz,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "updateClazz";
        }
        boolean updated = clazzService.update(clazz);
        if (updated) {
            return "redirect:/clazzController/findAll";
        }
        model.addAttribute("error", "Failed to update class.");
        return "error";
    }

    @GetMapping("/delete")
    public String deleteClazz(@RequestParam("classId") int classId, Model model) {
        try {
            clazzService.delete(classId);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "clazzes"; // Return to the list of classes
        }
        return "redirect:/clazzController/findAll";
    }
}