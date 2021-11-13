package com.example.interfaces;

import com.example.data.models.MyException;
import com.example.data.models.CommandLine;

public interface Command {

    void execute(CommandLine commandLine) throws MyException;

    boolean checkCommand(String command);
}
