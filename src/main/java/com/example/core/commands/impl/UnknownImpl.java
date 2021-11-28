/*
package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnknownImpl implements Command {

    @Value("${application.commands.unknown.name:unknownCommand}")
    private String commandName;

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        throw new MyException(commandName);
    }

    @Override
    public boolean checkCommand(String command) {
        return true;
    }
}
*/
