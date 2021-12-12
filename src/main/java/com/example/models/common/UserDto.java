package com.example.models.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotBlank
    @NotNull
    private String userName;

    @NotBlank
    @NotNull
    private String password;

    public UserDto(@NotBlank @NotNull String userName, @NotBlank @NotNull String password) {
        this.userName = userName;
        this.password = password;
    }
}
