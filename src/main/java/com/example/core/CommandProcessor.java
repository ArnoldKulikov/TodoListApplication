package com.example.core;

import com.example.commands.*;
import com.example.dictionaries.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Data
public class CommandProcessor {

    ArrayList<Task> taskList = new ArrayList<>();
    CommandExecute commandExecute = new CommandExecute();

    public void executeCommand(String commandLine) {

        log.debug(commandLine);

        String[] command = commandLine.split(" ",2);
        String commandCode = command[0];

        if (commandCode.equals("add")) commandExecute.setCommand(new AddTask());
        else if (commandCode.equals("print")) commandExecute.setCommand(new PrintTaskList());
        else if (commandCode.equals("search")) commandExecute.setCommand(new SearchTask());
        else if (commandCode.equals("toggle")) commandExecute.setCommand(new ChangeTaskStatus());
        else if (commandCode.equals("delete")) commandExecute.setCommand(new DeleteTask());
        else if (commandCode.equals("edit")) commandExecute.setCommand(new EditTask());
        else if (commandCode.equals("quit")) System.exit(0);
        else {
            System.out.println(ErrorList.ERRORLIST.get("unknownCommand"));
            return;
        }

        commandExecute.execute(taskList, command.length==1?"null":command[1]);
        log.debug(taskList.toString());
    }
}

