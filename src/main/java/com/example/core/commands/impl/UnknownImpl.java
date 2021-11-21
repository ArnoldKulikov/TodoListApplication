package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.dictionaries.ErrorList;
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
public class UnknownImpl implements Command {

    @Value("${application.commands.unknown.name}")
    private String commandName;
    private final TaskListRepository taskListRepository;
    private final ErrorList errorList;

    @Override
    public void execute(CommandLine commandLine) {
        String message = errorList.getErrorList().get(commandName);
        System.out.println(message);
        log.error(message);
        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return true;
    }

    @Override
    public String toString() {
        return "UnknownImpl{" +
                "commandName='" + commandName + '\'' +
                '}';
    }
}
