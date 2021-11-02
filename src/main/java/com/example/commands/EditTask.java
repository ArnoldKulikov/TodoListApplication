package com.example.commands;

import com.example.core.TaskList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
public class EditTask implements Command {

    private TaskList taskList;

    public EditTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) {
        if (commandLine.length == 1) {
            String errorMsg = ErrorList.ERRORLIST.get("notTaskId");
            log.error(errorMsg);
            System.out.println(errorMsg);
            return;
        }
        String[] argument = commandLine[1].split(" ", 2);
        if (!argument[0].matches("^[0-9]*$")) {
            String errorMsg = ErrorList.ERRORLIST.get("notTaskId");
            log.error(errorMsg);
            System.out.println(errorMsg);
            return;
        }
        if (argument.length == 1) {
            String errorMsg = ErrorList.ERRORLIST.get("emptyTaskDescription");
            log.error(errorMsg);
            System.out.println(errorMsg);
            return;
        }
        Optional<Task> foundTask = taskList.getTaskList()
                .stream()
                .filter(t -> t.getId() == Integer.parseInt(argument[0]))
                .findFirst();
        if (foundTask.isPresent()) foundTask.get().setDescription(argument[1]);
        else {
            String errorMsg = ErrorList.ERRORLIST.get("taskNotFound");
            log.error(errorMsg);
            System.out.println(errorMsg);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("edit");
    }
}
