package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.exeption.MyException;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.parsers.CommandLine;
import com.example.data.models.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddTaskImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        String description = commandLine.getDescription();

        if (description == null) {
            throw new MyException("emptyTaskDescription");
        }

        taskListRepository.createTask(description);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "add".equals(command);
    }
}
