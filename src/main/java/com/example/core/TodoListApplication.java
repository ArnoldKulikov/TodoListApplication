package com.example.core;

import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import com.example.parsers.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class TodoListApplication {

    @Value("${application.commands.quit.name}")
    private String commandsQuitName;
    private final CommandProcessor processor;
    private final Editor editor;
    private final Parser parser;

    public void run() {

        log.info("Программа запущена");

        CommandLine commandLine = parser.parseLine(editor.read());

        while (!commandsQuitName.equals(commandLine.getName())) {
            try {
                processor.executeCommand(commandLine);
            } catch (MyException | NumberFormatException e) {
                editor.write(e.getMessage());
                log.error(e.getMessage());
            }
            commandLine = parser.parseLine(editor.read());
        }

        log.info("Программа завершена");
    }
}
