package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.impl.TaskListRepositoryImpl;
import com.example.data.models.Task;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SearchTaskImpl implements Command {

    private TaskListRepository taskListRepository = new TaskListRepositoryImpl();

    @Autowired
    Editor editor;

    @Override
    public void execute(CommandLine commandLine) {
        if (commandLine.getDescription() != null) {
            for (Task task : taskListRepository.getTaskByDescription(commandLine.getDescription())) {
                editor.write(task.toString());
            }
        }
        log.debug(taskListRepository.getAllTasks().toString());
    }

    @Override
    public boolean checkCommand(String command) {
        return "search".equals(command);
    }
}
