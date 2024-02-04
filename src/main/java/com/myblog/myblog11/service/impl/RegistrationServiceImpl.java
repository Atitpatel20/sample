package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.entity.Registration;
import com.myblog.myblog11.payload.RegistrationDto;
import com.myblog.myblog11.repository.RegistrationRepository;
import com.myblog.myblog11.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public RegistrationDto createPost(RegistrationDto registrationDto) {

        Registration registration = mapToEntity(registrationDto);
        Registration dtos = registrationRepository.save(registration);
        return mapToDto(dtos);
    }

    @Override
    public RegistrationDto getPostById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Registration not found with id" + id)
        );
        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }

    @Override
    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Registration> pagePost = registrationRepository.findAll(pageable);
        List<Registration> registrations = pagePost.getContent();
        List<RegistrationDto> collect = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteRegistrationById(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegostration(long id, RegistrationDto registrationDto) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record not found with id")
        );
//        registration.setId(registrationDto.getId()); // its gives if we write
        registration.setName(registrationDto.getName());
        registration.setCity(registrationDto.getCity());
        registration.setMobile(registrationDto.getMobile());
        registration.setEmail(registrationDto.getEmail());

        Registration update = registrationRepository.save(registration);
        return mapToDto(update);
    }

    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dtos = modelMapper.map(registration, RegistrationDto.class);
        return dtos;
    }
    Registration mapToEntity(RegistrationDto registrationDto){
        Registration dtos = modelMapper.map(registrationDto, Registration.class);
        return dtos;
    }
}
