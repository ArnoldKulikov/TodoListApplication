package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.dictionaries.ErrorList;
import com.example.data.models.Task;
/*
import com.example.exeption.MyException;
*/
import com.example.parsers.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class AddTaskImpl implements Command {

    private String commandName;
    private final TaskListRepository taskListRepository;
    private final ErrorList errorList;

    @Autowired
    public AddTaskImpl(@Value("${application.commands.add.name}") String commandName, TaskListRepository taskListRepository, ErrorList errorList) {
        this.commandName = commandName;
        this.taskListRepository = taskListRepository;
        this.errorList = errorList;
    }

    @Override
    public void execute(CommandLine commandLine) /*throws MyException*/ {
        Task task = new Task();

        String description = commandLine.getDescription();

        if (description == null) {
            /*throw new MyException("emptyTaskDescription");*/
            System.out.println(errorList.getErrorList().get("emptyTaskDescription"));
            log.debug(taskListRepository.getAllTasks().toString());
            return;
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
