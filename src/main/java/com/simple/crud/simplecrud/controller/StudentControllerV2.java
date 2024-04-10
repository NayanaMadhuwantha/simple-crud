package com.simple.crud.simplecrud.controller;

import com.simple.crud.simplecrud.dto.StudentDto;
import com.simple.crud.simplecrud.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentControllerV2 {

    private final StudentService studentService;

    public StudentControllerV2(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> studentDtos = studentService.findAll();
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto studentDto){
        StudentDto studentDtoSaved = studentService.save(studentDto);
        return new ResponseEntity<>(studentDtoSaved,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") long id){
        StudentDto studentDto = studentService.findById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDto> update(@PathVariable(value = "id") long id, @Valid @RequestBody StudentDto studentDto){
        StudentDto studentDtoUpdated = studentService.update(id, studentDto);
        return new ResponseEntity<>(studentDtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") long id){
        studentService.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

}
