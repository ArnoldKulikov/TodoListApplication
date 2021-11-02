package com.example.core;

import com.example.commands.*;
import com.example.interfaces.Command;
import lombok.Data;

import java.util.List;

@Data
public class CommandList {
    private List<Command> commandList;

    public CommandList(TaskList taskList) {
       commandList = List.of(
                new AddTask(taskList),
                new ChangeTaskStatus(taskList),
                new DeleteTask(taskList),
                new EditTask(taskList),
                new PrintTaskList(taskList),
                new SearchTask(taskList));
    }
}
