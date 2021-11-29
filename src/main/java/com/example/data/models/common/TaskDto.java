package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class TaskDto {

    @NotBlank
    @NotNull
    private Long id;
    @NotBlank
    @NotNull
    private boolean closed;
    @NotBlank
    @NotNull
    private String description;

    public TaskDto taskServices(String description) {
        return new TaskDto()
                .setClosed(false)
                .setDescription(description);
    }
}