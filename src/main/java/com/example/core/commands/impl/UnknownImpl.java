package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
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
public class UnknownImpl implements Command {

    @Value("${application.commands.unknown.name}")
    private final String commandName;
    private final TaskListRepository taskListRepository;

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        log.debug(taskListRepository.getAllTasks().toString());
        throw new MyException(commandName);
    }

    @Override
    public boolean checkCommand(String command) {
        return true;
    }
}
