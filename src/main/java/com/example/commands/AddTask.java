package com.example.commands;

import com.example.core.MyException;
import com.example.core.TaskList;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;

public class AddTask implements Command {
    private TaskList taskList;

    public AddTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) throws MyException {

        if (commandLine.length == 1) {
            throw new MyException("emptyTaskDescription");
        } else {
            Task task = new Task (false, commandLine[1]);
            taskList.getTaskList().add(task);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "add".equals(command);
    }
}
