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

    @Value("${commands.quit.name}") //как вытащить конкретное значение из конфига если там список значений
    private String commandsQuitName;

    @Autowired
    CommandProcessor processor;

    public void run() {

        log.info("Программа запущена");
        System.out.println("Программа запущена");

        CommandLine commandLine = Parser.parseLine(Editor.read());

        while (!commandsQuitName.equals(commandLine.getName())) {
            try {
                System.out.println("Выполнение команды");
                processor.executeCommand(commandLine);
                System.out.println("Команда выполнена");
            } catch (MyException | NumberFormatException e) {
                Editor.write(e.getMessage());
                log.error(e.getMessage());
                System.out.println(e.getMessage());
            }
            commandLine = Parser.parseLine(Editor.read());
        }

        log.info("Программа завершена");
    }
}
