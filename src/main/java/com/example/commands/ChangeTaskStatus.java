package com.example.commands;

import com.example.core.MyException;
import com.example.core.TaskList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;
import java.util.Optional;


public class ChangeTaskStatus implements Command {

    private TaskList taskList;

    public ChangeTaskStatus(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) throws MyException {

        if (commandLine.length == 1 || !commandLine[1].matches("^[0-9]*$")) {
            throw new MyException(ErrorList.ERRORLIST.get("notTaskId"));
        }
        else {
            Optional<Task> foundTask = taskList.getTaskList()
                .stream()
                .filter(t -> t.getId() == Integer.parseInt(commandLine[1]))
                .findFirst();
            if (foundTask.isPresent()) foundTask.get().setClosed(!foundTask.get().isClosed());
            else {
                throw new MyException(ErrorList.ERRORLIST.get("taskNotFound"));
            }
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "toggle".equals(command);
    }
}
