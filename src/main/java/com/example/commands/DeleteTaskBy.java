package com.example.commands;

import com.example.core.Task;
import java.util.ArrayList;

public class DeleteTaskBy {
    public void id(ArrayList<Task> taskList, Task task) {
        taskList.remove(task);
    }
}
