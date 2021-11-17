package com.example.data.models;

import lombok.Data;

@Data
public class Task {
    private static Long nextTaskId = 1L;
    private Long id;
    private boolean isClosed;
    private String description;

    public Task(boolean isClosed, String description) {
        this.id = nextTaskId++;
        this.isClosed = isClosed;
        this.description = description;
    }

    public Task(Long id, boolean isClosed, String description) {
        this.id = id;
        this.isClosed = isClosed;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + ". " + (isClosed ? "[x]" : "[ ]") + " " + description;
    }
}
