package com.simple.crud.simplecrud.controller;

import com.simple.crud.simplecrud.dto.StudentDto;
import com.simple.crud.simplecrud.service.StudentService;
import com.simple.crud.simplecrud.util.Response;
import com.simple.crud.simplecrud.util.ResponseBuilder;
import com.simple.crud.simplecrud.util.ResponseError;
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
    public ResponseEntity<Response<List<StudentDto>>> findAll() {

        try {
            List<StudentDto> studentDtos = studentService.findAll();

            Response<List<StudentDto>> response = new ResponseBuilder<List<StudentDto>>()
                    .addData(studentDtos)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            ResponseError error = new ResponseError(500, "Internal Server Error", e.getMessage());

            Response<List<StudentDto>> response = new ResponseBuilder<List<StudentDto>>()
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping
    public ResponseEntity<Response<StudentDto>> save(@Valid @RequestBody StudentDto studentDto) {

        try {
            StudentDto studentDtoSaved = studentService.save(studentDto);

            Response<StudentDto> response = new ResponseBuilder<StudentDto>()
                    .addData(studentDtoSaved)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponseError error = new ResponseError(500, "Internal Server Error", e.getMessage());

            Response<StudentDto> response = new ResponseBuilder<StudentDto>()
                    .error(error)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") long id) {
        StudentDto studentDto = studentService.findById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDto> update(@PathVariable(value = "id") long id, @Valid @RequestBody StudentDto studentDto) {
        StudentDto studentDtoUpdated = studentService.update(id, studentDto);
        return new ResponseEntity<>(studentDtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
