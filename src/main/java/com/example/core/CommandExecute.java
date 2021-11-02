package com.example.core;

import com.example.interfaces.Command;
import lombok.Data;

import java.util.List;

@Data
public class CommandExecute {
    private Command command;

    public void execute(List<Task> taskList, String commandLine) {
        command.execute(taskList, commandLine);
    }
}
