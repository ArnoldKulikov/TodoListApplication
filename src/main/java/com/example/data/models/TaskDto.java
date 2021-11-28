package com.example.data.models;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class TaskDto {

    private Long id;
    private boolean isClosed;
    @NotBlank
    private String description;

    @Override
    public String toString() {
        return id + ". " + (isClosed ? "[x]" : "[ ]") + " " + description;
    }
}
