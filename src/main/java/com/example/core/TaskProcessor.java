package com.example.core;

import com.example.commands.*;
import com.example.dictionaries.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Optional;

@Data
public class TaskProcessor {

    CommandList commandList = new CommandList();
    SettingsList settingsList = new SettingsList();
    ArrayList<Task> taskList = new ArrayList<>();
    ErrorExecuteBy errorExecuteBy = new ErrorExecuteBy();

    public void executeCommand(String commandLine) {
        Logback.logger.debug(commandLine);

        String[] command = commandLine.split(" ",2);

        if(!commandList.getCommandList().containsKey(command[0])) {
            errorExecuteBy.idOnConsole("unknownCommand");
            return;
        }

        String commandCode = commandList.getCommandList().get(command[0]);

        if ("addCommand".equals(commandCode)) {
            AddTaskInTaskList addCommand = new AddTaskInTaskList();

            if(!settingsList.getSettingsList().containsKey("nextTaskId")) {
                errorExecuteBy.idOnConsole("unknownCommand");
                return;
            }

            int nextTaskId = settingsList.getSettingsList().get("nextTaskId");

            addCommand.addTask(taskList, nextTaskId, command[1]);
            settingsList.getSettingsList().put("nextTaskId", ++nextTaskId);
        }

        else if ("printCommand".equals(commandCode)) {
            PrintTaskListOn printTaskListOn = new PrintTaskListOn();

            if (command.length == 1) {
                printTaskListOn.console(taskList, true);
            }
            else if (command[1].equals("all")) {
                printTaskListOn.console(taskList, false);
            }
            else errorExecuteBy.idOnConsole("unknownSubCommand");
        }

        else if ("searchCommand".equals(commandCode)) {
            SearchTaskBy searchTaskBy = new SearchTaskBy();
            PrintTaskListOn printTaskListOn = new PrintTaskListOn();

            printTaskListOn.console(searchTaskBy.description(taskList, command[1]), false);
        }

        else if ("toggleCommand".equals(commandCode)) {
            SearchTaskBy searchTaskBy = new SearchTaskBy();

            Optional<Task> foundTask = searchTaskBy.id(taskList, Integer.parseInt(command[1]));

            if (foundTask.isPresent()) {
                ChangeTaskStatusBy changeTaskStatusBy = new ChangeTaskStatusBy();

                changeTaskStatusBy.id(foundTask.get());
            }
            else
                errorExecuteBy.idOnConsole("taskNotFound");

        }

        else if ("deleteCommand".equals(commandCode)) {
            SearchTaskBy searchTaskBy = new SearchTaskBy();

            Optional<Task> foundTask = searchTaskBy.id(taskList, Integer.parseInt(command[1]));

            if (foundTask.isPresent()) {
                DeleteTaskBy deleteTaskBy = new DeleteTaskBy();

                deleteTaskBy.id(taskList,foundTask.get());
            }
            else
                errorExecuteBy.idOnConsole("taskNotFound");
        }

        else if ("editCommand".equals(commandCode)) {
            String[] argument = command[1].split(" ", 2);

            SearchTaskBy searchTaskBy = new SearchTaskBy();

            Optional<Task> foundTask = searchTaskBy.id(taskList, Integer.parseInt(argument[0]));

            if (foundTask.isPresent()) {
                EditTaskBy editTaskBy = new EditTaskBy();

                editTaskBy.id(foundTask.get(), argument[1]);
            }
            else
                errorExecuteBy.idOnConsole("taskNotFound");
        }

        else if ("quitCommand".equals(commandCode)) {

            Logback.logger.info("Программа завершена");
            System.exit(0);
        }

        else errorExecuteBy.idOnConsole("unknownCommand");

        Logback.logger.debug(taskList.toString());
    }
}

