package com.example.commands;

import com.example.core.PrintExecute;
import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.prints.PrintAll;
import com.example.prints.PrintOpen;

import java.util.List;

public class PrintTaskList implements Command {

    PrintExecute printExecute = new PrintExecute();

    @Override
    public void execute(List<Task> taskList, String commandLine) {
        if (commandLine.equals("null")) printExecute.setPrint(new PrintOpen());
        else if (commandLine.equals("all")) printExecute.setPrint(new PrintAll());
        else {
            System.out.println(ErrorList.ERRORLIST.get("unknownSubCommand"));
            return;
        }

        printExecute.execute(taskList);
    }
}
