package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.exeption.MyException;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.parsers.CommandLine;
import com.example.data.models.Task;
import com.example.parsers.Editor;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PrintTaskListImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        List<Task> printingList;
        String argument = commandLine.getArgument();

        if (argument == null)
            printingList = taskListRepository.getTaskByStatus(false);
        else if ("all".equals(argument))
            printingList = taskListRepository.getAllTasks();
        else {
            throw new MyException("unknownSubCommand");
        }
        for (Task task : printingList) {
            Editor.write(task.toString());
        }

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "print".equals(command);
    }
}
