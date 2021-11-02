package com.example.commands;

import com.example.core.MyException;
import com.example.core.TaskList;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;

public class PrintTaskList implements Command {

    private TaskList taskList;

    public PrintTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) throws MyException {
        if (commandLine.length == 1)
            taskList.getTaskList().stream()
                    .filter(t -> t.isClosed() == false)
                    .forEach(System.out::println);
        else if ("all".equals(commandLine[1]))
            taskList.getTaskList()
                    .forEach(System.out::println);
        else {
            throw new MyException(ErrorList.ERRORLIST.get("unknownSubCommand"));
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "print".equals(command);
    }
}
