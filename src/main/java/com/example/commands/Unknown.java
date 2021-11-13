package com.example.commands;

import com.example.core.MyException;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;

public class Unknown implements Command {
    @Override
    public void execute(String[] commandLine) throws MyException {
        throw new MyException("unknownCommand");
    }

    @Override
    public boolean checkCommand(String command)  {
        return true;
    }
}
