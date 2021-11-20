package com.example.core;

import com.example.exeption.MyException;
import com.example.parsers.CommandLine;
import com.example.parsers.Editor;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class TodoListApplication {

    @Value("${commands.quit.name}")
    private String commandsQuitName;

    @Autowired
    CommandProcessor processor;
    Editor editor;

    public void run() {

        log.info("Программа запущена");
        System.out.println("Программа запущена");

        CommandLine commandLine = Parser.parseLine(editor.read());

        while (!commandsQuitName.equals(commandLine.getName())) {
            try {
                processor.executeCommand(commandLine);
            } catch (MyException | NumberFormatException e) {
                editor.write(e.getMessage());
                log.error(e.getMessage());
            }
            commandLine = Parser.parseLine(editor.read());
        }

        log.info("Программа завершена");
    }
}
