package com.example.core;

import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TodoListApplication {

    private static TodoListApplication NEW_TASK_LIST;

    private TodoListApplication() {
    }

    public static TodoListApplication getInstance() {
        if (NEW_TASK_LIST == null) {
            synchronized (TodoListApplication.class) {
                if (NEW_TASK_LIST == null) {
                    NEW_TASK_LIST = new TodoListApplication();
                }
            }
        }
        return NEW_TASK_LIST;
    }

    public void run() {

        log.info("Программа запущена");

        CommandProcessor processor = new CommandProcessor();
        CommandLine commandLine = Parser.parseLine(Editor.read());

        while (!"quit".equals(commandLine.getName())) {
            try {
                processor.executeCommand(commandLine);
            } catch (MyException | NumberFormatException e) {
                Editor.write(e.getMessage());
                log.error(e.getMessage());
            }
            commandLine = Parser.parseLine(Editor.read());
        }

        log.info("Программа завершена");
    }
}
