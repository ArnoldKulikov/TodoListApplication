package com.example.prints;

import com.example.core.Task;
import com.example.interfaces.Print;

import java.util.List;

public class PrintAll implements Print {
    @Override
    public void execute(List<Task> taskList) {
        taskList
                .forEach(System.out::println);
    }
}
