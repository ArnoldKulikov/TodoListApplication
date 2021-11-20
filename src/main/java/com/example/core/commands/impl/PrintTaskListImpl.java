package com.example.core.commands.impl;

import com.example.core.CommandProcessor;
import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.exeption.MyException;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.parsers.CommandLine;
import com.example.data.models.Task;
import com.example.parsers.Editor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PrintTaskListImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Autowired
    Editor editor;

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
            editor.write(task.toString());
        }

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "print".equals(command);
    }
}
