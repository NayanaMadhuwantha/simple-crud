package com.simple.crud.simplecrud.service.impl;

import com.simple.crud.simplecrud.dto.StudentDto;
import com.simple.crud.simplecrud.dto.UserDto;
import com.simple.crud.simplecrud.entity.User;
import com.simple.crud.simplecrud.repository.UserRepository;
import com.simple.crud.simplecrud.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto save(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setName(savedUser.getName());
        savedUserDto.setEmail(savedUser.getEmail());

        return savedUserDto;
    }

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public UserDto update(long id, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
