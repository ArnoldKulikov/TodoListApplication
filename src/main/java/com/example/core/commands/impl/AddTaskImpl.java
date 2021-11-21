package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.models.Task;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class AddTaskImpl implements Command {

    @Value("${application.commands.add.name}")
    private String commandName;
    private final TaskListRepository taskListRepository;

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        Task task = new Task();
        String description = commandLine.getDescription();

        if (description == null) {
            throw new MyException("emptyTaskDescription");
        }

        task
            .setClosed(false)
            .setDescription(description);

        taskListRepository.createTask(task);

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return commandName.equals(command);
    }
}
