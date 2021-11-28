package com.example.data.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeTaskRequestDto {

    @NotNull
    private String description;
    @NotNull
    private boolean closed;

}
