package com.example.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Task {

    private Long id;
    private boolean isClosed;
    private String description;

    @Override
    public String toString() {
        return id + ". " + (isClosed ? "[x]" : "[ ]") + " " + description;
    }
}
