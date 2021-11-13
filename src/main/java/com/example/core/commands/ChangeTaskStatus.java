package com.example.core.commands;

import com.example.data.models.MyException;
import com.example.data.base.TaskList;
import com.example.data.models.CommandLine;
import com.example.interfaces.Command;
import com.example.data.models.Task;

import java.util.Optional;


public class ChangeTaskStatus implements Command {

    private TaskList taskList;

    public ChangeTaskStatus(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(CommandLine commandLine) throws MyException {

        if (commandLine.getTaskId() == null) {
            throw new MyException("notTaskId");
        }
        else {
            Optional<Task> foundTask = taskList.getTaskList()
                .stream()
                .filter(t -> t.getId() == commandLine.getTaskId())
                .findFirst();
            if (foundTask.isPresent()) foundTask.get().setClosed(!foundTask.get().isClosed());
            else {
                throw new MyException("taskNotFound");
            }
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "toggle".equals(command);
    }
}
