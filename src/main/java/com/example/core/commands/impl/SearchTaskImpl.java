package com.example.core.commands.impl;

import com.example.core.commands.Command;
import com.example.data.TaskListRepository;
import com.example.data.models.Task;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class SearchTaskImpl implements Command {

    @Value("${application.commands.search.name}")
    private final String commandName;
    private final TaskListRepository taskListRepository;
    private final Editor editor;

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
        return commandName.equals(command);
    }
}
