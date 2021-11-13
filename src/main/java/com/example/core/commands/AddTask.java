package com.example.core.commands;

import com.example.data.models.MyException;
import com.example.data.base.TaskList;
import com.example.data.models.CommandLine;
import com.example.interfaces.Command;
import com.example.data.models.Task;

public class AddTask implements Command {
    private TaskList taskList;

    public AddTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(CommandLine commandLine) throws MyException {

        if (commandLine.getDescription() == null) {
            throw new MyException("emptyTaskDescription");
        } else {
            Task task = new Task (false, commandLine.getDescription());
            taskList.getTaskList().add(task);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "add".equals(command);
    }
}
