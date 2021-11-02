package com.example.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

}
