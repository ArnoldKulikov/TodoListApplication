package com.example.core.commands;

import com.example.data.models.MyException;
import com.example.data.models.CommandLine;
import com.example.interfaces.Command;

public class Unknown implements Command {
    @Override
    public void execute(CommandLine commandLine) throws MyException {
        throw new MyException("unknownCommand");
    }

    @Override
    public boolean checkCommand(String command)  {
        return true;
    }
}
