package com.example.interfaces;

import com.example.core.MyException;

public interface Command {

    void execute(String[] commandLine) throws MyException;

    boolean checkCommand(String command);
}
