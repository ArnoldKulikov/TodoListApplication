package com.example.core;

import com.example.core.commands.Command;
/*import com.example.exeption.MyException;*/
import com.example.parsers.CommandLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final List<Command> commandList;

    public void executeCommand(CommandLine commandLine) /*throws MyException*/ {

        System.out.println("Выполнение команды в executeCommand");
        for (Command command : commandList) {
            if (command.checkCommand(commandLine.getName())) {
                command.execute(commandLine);
                return;
            }
        }
    }
}

