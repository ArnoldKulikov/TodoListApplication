/*
package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import com.example.data.models.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class EditTaskImpl implements Command {

    @Value("${application.commands.edit.name}")
    private final String commandName;
    private final TaskListRepository taskListRepository;

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

        Task task = taskListRepository.getTaskById(taskId);
        task.setDescription(description);
        taskListRepository.updateTask(task);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return commandName.equals(command);
    }
}
*/
