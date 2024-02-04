package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto createPost(RegistrationDto registrationDto);

    RegistrationDto getPostById(long id);

    List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteRegistrationById(long id);

    RegistrationDto updateRegostration(long id, RegistrationDto registrationDto);
}
