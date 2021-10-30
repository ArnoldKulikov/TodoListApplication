package com.example.core;

import lombok.Data;

@Data
public class Task {
    private int id;
    private boolean isClosed;
    private String description;

    public Task(int id, boolean isClosed, String description) {
        this.id = id;
        this.isClosed = isClosed;
        this.description = description;
    }

    @Override
    public String toString() {
        return  id + ". " + (isClosed?"[x]":"[ ]") + " " + description;
    }
}
