package com.simple.crud.simplecrud.repository;

import com.simple.crud.simplecrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
