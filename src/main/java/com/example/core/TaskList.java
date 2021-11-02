package com.example.core;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

}
