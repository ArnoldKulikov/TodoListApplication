package com.example.core;

import com.example.interfaces.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandProcessor {

    private TaskList taskList = new TaskList();
    private CommandList commandList = new CommandList(taskList);

    public void executeCommand(String commandLine) {

        log.debug(commandLine);

        String[] commandArray = commandLine.split(" ",2);
        String commandCode = commandArray[0];

        for (Command command: commandList.getCommandList()) {
            if (command.checkCommand(commandCode)) {
                command.execute(commandArray);
                break;
            }
        }
        log.debug(taskList.getTaskList().toString());
    }
}

