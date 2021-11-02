package com.example.commands;

import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;

import java.util.List;
import java.util.Optional;

public class EditTask implements Command {
    @Override
    public void execute(List<Task> taskList, String commandLine) {
        String[] argument = commandLine.split(" ", 2);

        if (!argument[0].matches("^[0-9]*$")) System.out.println(ErrorList.ERRORLIST.get("notTaskId"));
        else {
            Optional<Task> foundTask = taskList
                    .stream()
                    .filter(t -> t.getId() == Integer.parseInt(argument[0]))
                    .findFirst();
            if (foundTask.isPresent()) foundTask.get().setDescription(argument[1]);
            else System.out.println(ErrorList.ERRORLIST.get("taskNotFound"));
        }
    }
}
