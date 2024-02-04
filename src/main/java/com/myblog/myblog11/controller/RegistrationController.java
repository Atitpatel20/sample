package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.RegistrationDto;
import com.myblog.myblog11.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {


    private RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createPost(@RequestBody RegistrationDto registrationDto) {
        RegistrationDto dto = registrationService.createPost(registrationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getPostById(@RequestParam long id) {
        RegistrationDto dto = registrationService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    // http://localhost:8080/api/registrations?pageNo=0&pageSize=3&sortBy=id&sortDir=desc
    @GetMapping
    public List<RegistrationDto> getAllRegistration(
            @RequestParam(name="pageNo",defaultValue="0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue="3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue="id",required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue="asc",required = false) String sortDir

    ) {
        List<RegistrationDto> dtos = registrationService.getAllRegistration(pageNo,pageSize,sortBy,sortDir);
        return dtos;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistrationById(@PathVariable long id) {
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("Record is deleted", HttpStatus.OK);
    }

    // http://localhost:8080/api/registrations
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegostration(@PathVariable long id,@RequestBody RegistrationDto registrationDto) {
        RegistrationDto dto= registrationService.updateRegostration(id,registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
