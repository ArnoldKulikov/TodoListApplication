package com.example.commands;

import com.example.interfaces.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Quit implements Command {
    @Override
    public void execute(String[] commandLine) {
        log.info("Программа завершена");
        System.exit(0);
    }

    @Override
    public boolean checkCommand(String command) {
        return "quit".equals(command);
    }
}
