package com.example.models.common;

import com.example.entities.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

}
