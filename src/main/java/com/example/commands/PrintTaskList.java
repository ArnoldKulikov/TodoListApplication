package com.example.commands;

import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PrintTaskList implements Command {

    @Override
    public void execute(List<Task> taskList, String[] commandLine) {
        if (commandLine.length == 1)
            taskList.stream()
                    .filter(t -> t.isClosed() == false)
                    .forEach(System.out::println);
        else if (commandLine[1].equals("all"))
            taskList
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
