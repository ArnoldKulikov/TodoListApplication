package com.example.core;

import com.example.interfaces.Command;
import com.example.interfaces.Print;
import lombok.Data;

import java.util.List;

@Data
public class PrintExecute {
    private Print print;

    public void execute(List<Task> taskList) {
        print.execute(taskList);
    }
}
