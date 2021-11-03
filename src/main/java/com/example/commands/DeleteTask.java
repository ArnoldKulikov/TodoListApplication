package com.example.commands;

import com.example.core.MyException;
import com.example.core.TaskList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;
import java.util.Optional;

public class DeleteTask implements Command {

    private TaskList taskList;

    public DeleteTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) throws MyException {
        if (commandLine.length == 1 || !commandLine[1].matches("^[0-9]*$")) {
            throw new MyException("notTaskId");
        }
        else {
            Optional<Task> foundTask = taskList.getTaskList()
                    .stream()
                    .filter(t -> t.getId() == Integer.parseInt(commandLine[1]))
                    .findFirst();
            if (foundTask.isPresent()) taskList.getTaskList().remove(foundTask.get());
            else {
                throw new MyException("taskNotFound");
            }
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "delete".equals(command);
    }
}
