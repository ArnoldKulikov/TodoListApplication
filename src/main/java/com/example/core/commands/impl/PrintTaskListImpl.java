/*
package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.models.TaskDto;
import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrintTaskListImpl implements Command {

    @Value("${application.commands.print.name:print}")
    private String commandName;
    @Value("${application.commands.print.arg:all}")
    private String commandArgName;
    private final TaskListRepository taskListRepository;
    private final Editor editor;

    @Override
    public void execute(CommandLine commandLine) throws MyException {
        List<TaskDto> printingList;
        String argument = commandLine.getArgument();

        if (argument == null)
            printingList = taskListRepository.getTaskByStatus(false);
        else if (commandArgName.equals(argument))
            printingList = taskListRepository.getAllTasks();
        else {
            throw new MyException("unknownSubCommand");
        }
        for (TaskDto taskDto : printingList) {
            editor.write(taskDto.toString());
        }

        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return commandName.equals(command);
    }
}
*/
