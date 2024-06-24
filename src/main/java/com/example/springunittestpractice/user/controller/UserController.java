package com.example.springunittestpractice.user.controller;

import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserUpdateDto;
import com.example.springunittestpractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    ResponseEntity<?> createNewUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @GetMapping("{userId}")
    ResponseEntity<?> findUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @PutMapping("{userId}")
    ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userUpdateDto));
    }
}
