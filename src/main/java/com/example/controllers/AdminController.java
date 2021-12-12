package com.example.controllers;

import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.UserDto;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto user) throws MyException {
        Boolean result = userService.saveUser(user);
        if(!result) {
            throw new MyException("userExist");
        }
        UserDetails localUser = userService.loadUserByUsername(user.getUserName());
        return new UserDto(localUser.getUsername(),localUser.getPassword());
    }

    @DeleteMapping("/{user_id}")
    public void deleteTask(@PathVariable("user_id") @NonNull Long taskId) throws EmptyResultDataAccessException {
        userService.deleteUser(taskId);
    }
}
