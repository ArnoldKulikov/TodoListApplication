package com.example.commands;

import com.example.core.TaskList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
public class ChangeTaskStatus implements Command {

    private TaskList taskList;

    public ChangeTaskStatus(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) {

        if (commandLine.length == 1 || !commandLine[1].matches("^[0-9]*$")) {
            String errorMsg = ErrorList.ERRORLIST.get("notTaskId");
            log.error(errorMsg);
            System.out.println(errorMsg);
        }
        else {
            Optional<Task> foundTask = taskList.getTaskList()
                .stream()
                .filter(t -> t.getId() == Integer.parseInt(commandLine[1]))
                .findFirst();
            if (foundTask.isPresent()) foundTask.get().setClosed(!foundTask.get().isClosed());
            else {
                String errorMsg = ErrorList.ERRORLIST.get("taskNotFound");
                log.error(errorMsg);
                System.out.println(errorMsg);
            }
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "toggle".equals(command);
    }
}
