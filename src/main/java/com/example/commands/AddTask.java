package com.example.commands;

import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AddTask implements Command {

    @Override
    public void execute(List<Task> taskList, String[] commandLine) {

        if (commandLine.length == 1) {
            String errorMsg = ErrorList.ERRORLIST.get("emptyTaskDescription");
            log.error(errorMsg);
            System.out.println(errorMsg);
        } else {

            int nextTaskId = Task.nextTaskId;

            Task task = new Task(nextTaskId, false, commandLine[1]);
            taskList.add(task);
            Task.nextTaskId = ++nextTaskId;
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("add");
    }
}
