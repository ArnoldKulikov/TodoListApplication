package com.example.commands;

import com.example.core.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PrintTaskListOn {
    public void console(ArrayList<Task> taskList, boolean onlyOpen) {
        Stream<Task> currentTaskList = taskList.stream();

        if (onlyOpen) {
            currentTaskList
                    .filter(t -> t.isClosed() == !onlyOpen)
                    .forEach(System.out::println);
        } else {
            currentTaskList
                    .forEach(System.out::println);
        }
    }
}
