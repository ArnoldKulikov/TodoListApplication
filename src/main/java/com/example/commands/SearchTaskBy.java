package com.example.commands;

import com.example.core.Task;

import java.util.ArrayList;
import java.util.Optional;

public class SearchTaskBy {
    public ArrayList<Task> description(ArrayList<Task> taskList, String searchText) {
        ArrayList<Task> resultTaskList = new ArrayList<>();

        taskList
                .stream()
                .filter(t -> t.getDescription().contains(searchText))
                .forEach(resultTaskList::add);
        return resultTaskList;
    }

    public Optional<Task> id(ArrayList<Task> taskList, int taskId) {

        return taskList
                .stream()
                .filter(t -> t.getId() == taskId)
                .findFirst();
    }
}
