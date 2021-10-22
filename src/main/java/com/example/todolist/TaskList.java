package com.example.todolist;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    private int nextTaskId = 1;
    private String taskNotFound = "Задача не найдена";

    public void addTask(String task) {
        Task receivedTask = new Task(nextTaskId++, false, task);

        taskList.add(receivedTask);
    }

    public void printTaskList(boolean onlyOpen) {
        Stream<Task> currentTaskList = taskList.stream();

        if (onlyOpen) {
            currentTaskList
                    .filter(t -> t.getStatus() == !onlyOpen)
                    .forEach(System.out::println);
        } else {
            currentTaskList
                    .forEach(System.out::println);
        }
    }

    public void searchTaskByDescription(String searchText) {
        taskList
                .stream()
                .filter(t -> t.getDescription().contains(searchText))
                .forEach(System.out::println);
    }

    public void editTaskInTaskList(String taskInfo) {
        String[] command = taskInfo.split(" ", 2);
        int taskId = Integer.parseInt(command[0]);
        Optional<Task> foundTask = findTaskById(taskId);

        if (foundTask.isPresent()) foundTask.get().setDescription(command[1]);
        else System.out.println(taskNotFound);
    }

    public void changeTaskStatus(int taskId) {
        Optional<Task> foundTask = findTaskById(taskId);

        if (foundTask.isPresent()) {
            Task currentTask = foundTask.get();

            currentTask.setStatus(!currentTask.getStatus());
        }
        else System.out.println(taskNotFound);
    }

    public void deleteTaskInTaskList(int taskId) {
        Optional<Task> foundTask = findTaskById(taskId);

        if (foundTask.isPresent()) taskList.remove(foundTask.get());
            else System.out.println(taskNotFound);
    }

    public Optional<Task> findTaskById(int taskId) {

        return taskList
                .stream()
                .filter(t -> t.getId() == taskId)
                .findFirst();
    }
}
