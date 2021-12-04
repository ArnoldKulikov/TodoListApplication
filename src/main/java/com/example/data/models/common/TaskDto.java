package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "tasks")
@Accessors(chain = true)
public class TaskDto {

    @NotBlank
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private Boolean closed;
    @NotBlank
    @NotNull
    private String description;

    public TaskDto taskServices(String description) {
        return new TaskDto()
                .setClosed(false)
                .setDescription(description);
    }
}