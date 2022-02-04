package com.example.controllers;

import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.UserDto;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
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
        if (!result) {
            throw new MyException("userExist");
        }
        User localUser = userService.loadUserByUsername(user.getUserName());
        return new UserDto(localUser.getUsername(), localUser.getPassword());
    }

    @DeleteMapping("/{user_id}")
    public void deleteTask(@PathVariable("user_id") @NonNull Long taskId) {
        userService.deleteUser(taskId);
    }
}
