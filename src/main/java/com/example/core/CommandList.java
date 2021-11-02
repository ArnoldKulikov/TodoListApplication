package com.example.core;

import com.example.commands.*;
import com.example.interfaces.Command;
import lombok.Data;

import java.util.List;

@Data
public class CommandList {
    private List<Command> commandList = List.of(
            new AddTask(),
            new ChangeTaskStatus(),
            new DeleteTask(),
            new EditTask(),
            new PrintTaskList(),
            new SearchTask()
    );
}
