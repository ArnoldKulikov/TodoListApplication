package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnknownImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        log.debug(taskListRepository.getAllTasks().toString());
        throw new MyException("unknownCommand");
    }

    @Override
    public boolean checkCommand(String command) {
        return true;
    }
}
