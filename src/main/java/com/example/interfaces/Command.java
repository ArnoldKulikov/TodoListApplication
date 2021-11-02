package com.example.interfaces;

public interface Command {

    void execute(String[] commandLine);

    boolean checkCommand(String command);
}
