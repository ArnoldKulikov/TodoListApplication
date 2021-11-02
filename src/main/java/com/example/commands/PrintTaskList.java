package com.example.commands;

import com.example.core.TaskList;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintTaskList implements Command {

    private TaskList taskList;

    public PrintTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) {
        if (commandLine.length == 1)
            taskList.getTaskList().stream()
                    .filter(t -> t.isClosed() == false)
                    .forEach(System.out::println);
        else if (commandLine[1].equals("all"))
            taskList.getTaskList()
                    .forEach(System.out::println);
        else {
            String errorMsg = ErrorList.ERRORLIST.get("unknownSubCommand");
            log.error(errorMsg);
            System.out.println(errorMsg);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("print");
    }
}
