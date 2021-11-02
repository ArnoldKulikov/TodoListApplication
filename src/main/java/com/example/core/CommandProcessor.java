package com.example.core;

import com.example.dictionaries.*;
import com.example.interfaces.Command;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Data
public class CommandProcessor {

    ArrayList<Task> taskList = new ArrayList<>();
    CommandList commandList = new CommandList();

    public void executeCommand(String commandLine) {

        log.debug(commandLine);
        boolean commandIsExecuted = false;

        String[] commandArray = commandLine.split(" ",2);
        String commandCode = commandArray[0];

        if (commandCode.equals("quit")) {
            log.info("Программа завершена");
            System.exit(0);
        }
        for (Command command: commandList.getCommandList()) {
            if (command.checkCommand(commandCode)) {
                command.execute(taskList, commandArray);
                commandIsExecuted = true;
            }
        }

        if (!commandIsExecuted) System.out.println(ErrorList.ERRORLIST.get("unknownCommand"));
        log.debug(taskList.toString());
    }
}

