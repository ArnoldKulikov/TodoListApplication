package com.example.data.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeTaskRequestDto {

    private String description;
    private Boolean closed;

}
