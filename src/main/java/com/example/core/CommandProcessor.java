package com.example.core;

import com.example.dictionaries.*;
import com.example.interfaces.Command;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CommandProcessor {

    private TaskList taskList = new TaskList();
    private CommandList commandList = new CommandList(taskList);

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
                command.execute(commandArray);
                commandIsExecuted = true;
            }
        }

        if (!commandIsExecuted) {
            String errorMsg = ErrorList.ERRORLIST.get("unknownCommand");
            log.error(errorMsg);
            System.out.println(errorMsg);
        }
        log.debug(taskList.getTaskList().toString());
    }
}

