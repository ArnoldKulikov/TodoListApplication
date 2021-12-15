package com.example.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateTaskRequestDto {

    @NotBlank
    @NotNull
    private String description;

}
