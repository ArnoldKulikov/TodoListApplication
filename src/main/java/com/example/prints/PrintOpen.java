package com.example.prints;

import com.example.core.Task;
import com.example.interfaces.Print;

import java.util.List;

public class PrintOpen implements Print {
    @Override
    public void execute(List<Task> taskList) {
        taskList.stream()
                .filter(t -> t.isClosed() == false)
                .forEach(System.out::println);
    }
}
