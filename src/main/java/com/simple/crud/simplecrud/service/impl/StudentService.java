package com.simple.crud.simplecrud.service.impl;

import com.simple.crud.simplecrud.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAll();

    void save(StudentDto student);

    StudentDto findById(long id);

    void deleteById(long id);

}
