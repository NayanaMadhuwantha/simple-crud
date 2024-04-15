package com.simple.crud.simplecrud.service;

import com.simple.crud.simplecrud.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto save(UserDto userDto);

    UserDto findById(long id);

    UserDto update(long id, UserDto userDto);

    void deleteById(long id);

}
