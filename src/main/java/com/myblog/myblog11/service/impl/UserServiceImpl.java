package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.entity.Registration;
import com.myblog.myblog11.entity.User;
import com.myblog.myblog11.payload.UserDto;
import com.myblog.myblog11.repository.RegistrationRepository;
import com.myblog.myblog11.repository.UserRepository;
import com.myblog.myblog11.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,RegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.registrationRepository=registrationRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto, @PathVariable long registrationId) {
        Registration registration = registrationRepository.findById(registrationId).orElseThrow(
                () -> new ResourceNotFoundException("registration not found with id " + registrationId)
        );
        User u = mapToEntity(userDto);
        u.setRegistration(registration);
        User saveUsers = userRepository.save(u);
        return mapToDto(saveUsers);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto, long registrationId) {
        Registration registration = registrationRepository.findById(registrationId).orElseThrow(
                () -> new ResourceNotFoundException("Registration not found with id" + registrationId)
        );
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found with id" + id)
        );
        User u = mapToEntity(userDto);
        u.setId(user.getId());
        u.setRegistration(registration);
        User dtos = userRepository.save(u);
        return mapToDto(dtos);
    }

    UserDto mapToDto(User user) {
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

    User mapToEntity(UserDto userDto) {
        User users = modelMapper.map(userDto, User.class);
        return users;
    }
}
