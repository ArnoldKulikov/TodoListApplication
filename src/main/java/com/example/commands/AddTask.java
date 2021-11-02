package com.example.commands;

import com.example.core.TaskList;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddTask implements Command {
    private TaskList taskList;

    public AddTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) {

        if (commandLine.length == 1) {
            String errorMsg = ErrorList.ERRORLIST.get("emptyTaskDescription");
            log.error(errorMsg);
            System.out.println(errorMsg);
        } else {
            Task task = new Task (false, commandLine[1]);
            taskList.getTaskList().add(task);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("add");
    }
}
