package com.example.data.base;

import com.example.data.models.Task;

import java.util.ArrayList;

public class TaskList {

    private static TaskList newTaskList;
    private ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return newTaskList.taskList;
    }

    public static TaskList getInstance(){
        if(newTaskList == null) newTaskList = new TaskList();
        return newTaskList;
    }

    private TaskList() {
    }
}

