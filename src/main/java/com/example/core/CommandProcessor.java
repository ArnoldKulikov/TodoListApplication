package com.example.core;

import com.example.data.base.TaskList;
import com.example.data.dictionaries.CommandList;
import com.example.data.models.CommandLine;
import com.example.data.models.MyException;
import com.example.interfaces.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandProcessor {

    private TaskList taskList = TaskList.getInstance();
    private CommandList commandList = new CommandList(taskList);

    public void executeCommand(CommandLine commandLine) throws MyException {

        for (Command command: commandList.getCommandList()) {
            if (command.checkCommand(commandLine.getName())) {
                command.execute(commandLine);
                break;
            }
        }
        log.debug(taskList.getTaskList().toString());
    }
}

