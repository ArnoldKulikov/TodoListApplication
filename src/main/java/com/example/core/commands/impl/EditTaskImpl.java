package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.models.TaskDto;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EditTaskImpl implements Command {

    @Value("${application.commands.edit.name:edit}")
    private String commandName;
    private final TaskListRepository taskListRepository;

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        Long taskId = commandLine.getTaskId();
        String description = commandLine.getDescription();

        if (taskId == null) {
            throw new MyException("notTaskId");
        }
        if (description == null || description.equals("")) {
            throw new MyException("emptyTaskDescription");
        }

        TaskDto taskDto = taskListRepository.getTaskById(taskId);
        taskDto.setDescription(description.trim());
        taskListRepository.updateTask(taskDto);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return commandName.equals(command);
    }
}
