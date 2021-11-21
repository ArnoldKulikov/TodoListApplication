package com.example.core.commands;

/*import com.example.exeption.MyException;*/
import com.example.parsers.CommandLine;

public interface Command {

    void execute(CommandLine commandLine) /*throws MyException*/;

    boolean checkCommand(String command);

}
