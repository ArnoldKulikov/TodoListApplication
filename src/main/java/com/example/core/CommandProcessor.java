package com.example.core;

import com.example.core.commands.Command;
import com.example.core.commands.impl.*;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CommandProcessor {

    private List<Command> commandList = List.of(
            new AddTaskImpl(),
            new ChangeTaskStatusImpl(),
            new DeleteTaskImpl(),
            new EditTaskImpl(),
            new PrintTaskListImpl(),
            new SearchTaskImpl(),
            new UnknownImpl());

    public void executeCommand(CommandLine commandLine) throws MyException {

        for (Command command : commandList) {
            if (command.checkCommand(commandLine.getName())) {
                command.execute(commandLine);
                return;
            }
        }
    }
}

