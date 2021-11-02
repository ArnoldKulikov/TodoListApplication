package com.example.core;

import lombok.Data;

@Data
public class Task {
    private static int nextTaskId = 1;
    private int id;
    private boolean isClosed;
    private String description;

    public Task(boolean isClosed, String description) {
        this.id = nextTaskId++;
        this.isClosed = isClosed;
        this.description = description;
    }

    @Override
    public String toString() {
        return  id + ". " + (isClosed?"[x]":"[ ]") + " " + description;
    }
}
