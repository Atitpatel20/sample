package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto, long registrationId);

    List<UserDto> getAllUsers();

    void deleteUser(long id);

    UserDto updateUser(long id, UserDto userDto, long registrationId);
}
