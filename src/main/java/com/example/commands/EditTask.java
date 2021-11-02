package com.example.commands;

import com.example.core.MyException;
import com.example.core.TaskList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;
import java.util.Optional;

public class EditTask implements Command {

    private TaskList taskList;

    public EditTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) throws MyException {
        if (commandLine.length == 1) {
            throw new MyException(ErrorList.ERRORLIST.get("notTaskId"));
        }
        String[] argument = commandLine[1].split(" ", 2);
        if (!argument[0].matches("^[0-9]*$")) {
            throw new MyException(ErrorList.ERRORLIST.get("notTaskId"));
        }
        if (argument.length == 1) {
            throw new MyException(ErrorList.ERRORLIST.get("emptyTaskDescription"));
        }
        Optional<Task> foundTask = taskList.getTaskList()
                .stream()
                .filter(t -> t.getId() == Integer.parseInt(argument[0]))
                .findFirst();
        if (foundTask.isPresent()) foundTask.get().setDescription(argument[1]);
        else {
            throw new MyException(ErrorList.ERRORLIST.get("taskNotFound"));
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "edit".equals(command);
    }
}
