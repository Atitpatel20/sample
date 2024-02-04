package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.UserDto;
import com.myblog.myblog11.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // http://localhost:8080/api/posts/1
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto,@RequestParam long registrationId) {
        UserDto userDtos= userService.createUser(userDto,registrationId);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @GetMapping
    public List<UserDto> getAllUsers(){
        List<UserDto> dtos=userService.getAllUsers();
        return dtos;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("user is deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}/registration/{registrationId}")
    public ResponseEntity<UserDto>updateUser(@PathVariable long id,@RequestBody UserDto userDto,@PathVariable long registrationId){
        UserDto dto= userService.updateUser(id,userDto,registrationId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
