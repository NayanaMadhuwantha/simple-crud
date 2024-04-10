package com.simple.crud.simplecrud.service;

import com.simple.crud.simplecrud.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAll();

    StudentDto save(StudentDto student);

    StudentDto findById(long id);

    StudentDto update(long id, StudentDto studentDto);

    void deleteById(long id);

}
