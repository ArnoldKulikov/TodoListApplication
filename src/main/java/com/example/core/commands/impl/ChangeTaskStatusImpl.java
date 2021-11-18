package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.data.models.Task;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeTaskStatusImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        Long taskId = commandLine.getTaskId();

        if (taskId == null) {
            throw new MyException("notTaskId");
        }

        Task task = taskListRepository.getTaskById(taskId);
        task.setClosed(!task.isClosed());
        taskListRepository.updateTask(task);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "toggle".equals(command);
    }
}
