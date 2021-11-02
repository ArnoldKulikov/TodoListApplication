package com.example.commands;

import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Unknown implements Command {
    @Override
    public void execute(String[] commandLine) {
        String errorMsg = ErrorList.ERRORLIST.get("unknownCommand");
        log.error(errorMsg);
        System.out.println(errorMsg);
    }

    @Override
    public boolean checkCommand(String command)  {
        return true;
    }
}
