package com.example.commands;

import com.example.core.Task;

public class EditTaskBy {
    public void id(Task task, String description) {
        task.setDescription(description);
}
}
