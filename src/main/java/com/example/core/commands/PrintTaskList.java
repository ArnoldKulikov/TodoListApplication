package com.example.core.commands;

import com.example.data.models.MyException;
import com.example.data.base.TaskList;
import com.example.data.models.CommandLine;
import com.example.interfaces.Command;

public class PrintTaskList implements Command {

    private TaskList taskList;

    public PrintTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        if (commandLine.getArgument() == null)
            taskList.getTaskList().stream()
                    .filter(t -> t.isClosed() == false)
                    .forEach(System.out::println);
        else if ("all".equals(commandLine.getArgument()))
            taskList.getTaskList()
                    .forEach(System.out::println);
        else {
            throw new MyException("unknownSubCommand");
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "print".equals(command);
    }
}
