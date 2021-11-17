package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.data.models.Task;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchTaskImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Override
    public void execute(CommandLine commandLine) {
        if (commandLine.getDescription() != null) {
            for (Task task : taskListRepository.getTaskByDescription(commandLine.getDescription())) {
                Editor.write(task.toString());
            }
        }
        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "search".equals(command);
    }
}
