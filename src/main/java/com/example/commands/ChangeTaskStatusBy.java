package com.example.commands;

import com.example.core.Task;

public class ChangeTaskStatusBy {
    public void id(Task task) {
        task.setClosed(!task.isClosed());
    }
}
