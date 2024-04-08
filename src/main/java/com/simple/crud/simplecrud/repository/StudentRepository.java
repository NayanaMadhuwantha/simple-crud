package com.simple.crud.simplecrud.repository;

import com.simple.crud.simplecrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
