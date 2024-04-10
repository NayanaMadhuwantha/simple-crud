package com.simple.crud.simplecrud.service.impl;

import com.simple.crud.simplecrud.dto.StudentDto;
import com.simple.crud.simplecrud.entity.Student;
import com.simple.crud.simplecrud.repository.StudentRepository;
import com.simple.crud.simplecrud.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> findAll() {
        //Entity list to DTO list
        return studentRepository.findAll()
                .stream()
                .map(student -> convertToDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());

        Student studentSaved = this.studentRepository.save(student);

        StudentDto studentDtoSaved = new StudentDto();
        studentDtoSaved.setId(studentSaved.getId());
        studentDtoSaved.setName(studentSaved.getName());
        studentDtoSaved.setAge(studentSaved.getAge());
        studentDtoSaved.setEmail(studentSaved.getEmail());

        return studentDtoSaved;
    }

    @Override
    public StudentDto findById(long id) {
        Optional<Student> optional = this.studentRepository.findById(id);

        Student student = null;
        StudentDto studentDto = new StudentDto();

        if (optional.isPresent()) {
            student = optional.get();

            studentDto.setId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setAge(student.getAge());
            studentDto.setEmail(student.getEmail());

        } else {
            throw new RuntimeException("Student not found");
        }

        return studentDto;

        //return optional.orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    @Override
    public StudentDto update(long id, StudentDto studentDto) {

        Optional<Student> optional = this.studentRepository.findById(id);

        if (optional.isPresent()) {
            Student student = optional.get();
            student.setName(studentDto.getName());
            student.setAge(studentDto.getAge());
            student.setEmail(studentDto.getEmail());

            Student studentSaved = this.studentRepository.save(student);

            StudentDto studentDtoUpdated = new StudentDto();
            studentDtoUpdated.setId(studentSaved.getId());
            studentDtoUpdated.setName(studentSaved.getName());
            studentDtoUpdated.setAge(studentSaved.getAge());
            studentDtoUpdated.setEmail(studentSaved.getEmail());

            return studentDtoUpdated;

        } else {
            throw new RuntimeException("Student not found");
        }

    }

    @Override
    public void deleteById(long id) {
        this.studentRepository.deleteById(id);
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }
}
