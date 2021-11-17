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

        if (commandLine.getDescription() == null) {
            throw new MyException("emptyTaskDescription");
        }

        Task task = new Task(false, commandLine.getDescription());
        taskListRepository.createTask(task);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "add".equals(command);
    }
}
