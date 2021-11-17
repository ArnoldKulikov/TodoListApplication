package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.exeption.MyException;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.parsers.CommandLine;
import com.example.data.models.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditTaskImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        Long taskId = commandLine.getTaskId();
        String description = commandLine.getDescription();

        if (taskId == null) {
            throw new MyException("notTaskId");
        }
        if (description == null) {
            throw new MyException("emptyTaskDescription");
        }

        Task task = new Task(taskId, taskListRepository.getTaskById(taskId).isClosed(), description);
        taskListRepository.updateTask(task);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "edit".equals(command);
    }
}
