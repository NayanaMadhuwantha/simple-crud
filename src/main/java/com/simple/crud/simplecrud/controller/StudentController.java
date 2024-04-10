package com.simple.crud.simplecrud.controller;

import com.simple.crud.simplecrud.dto.StudentDto;
import com.simple.crud.simplecrud.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String viewHome(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/index";
    }

    @GetMapping("/student")
    public String showSaveStudentForm(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "student/save";
    }

    @PostMapping("/student")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "student/save";
        }

        studentService.save(studentDto);
        return "redirect:/";
    }

    @GetMapping("/student/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        StudentDto studentDto = studentService.findById(id);
        model.addAttribute("student", studentDto);
        return "student/update";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        this.studentService.deleteById(id);
        return "redirect:/";
    }

}
