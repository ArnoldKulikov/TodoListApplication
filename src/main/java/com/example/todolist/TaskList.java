package com.example.todolist;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    private int nextTaskId = 0;
    private String taskNotFound = "Задача не найдена";

    public void setTaskList(String task) {
        Task receivedTask = new Task(++this.nextTaskId, false, task);
        this.taskList.add(receivedTask);
    }

    public void printTaskList(boolean onlyOpen) {
        if (this.getTaskListSize(onlyOpen) > 0) {
            if (onlyOpen) {
                for (Task taskList : taskList) {
                    if (!taskList.getStatus())
                        System.out.println(taskList);
                }
                return;
            }
            for (Task taskList : taskList) {
                System.out.println(taskList);
            }
        }
    }

    public void searchTaskInTaskList(String searchText) {
        for (Task nextTask : taskList) {
            if (nextTask.getDescription().contains(searchText)) System.out.println(nextTask);
        }
    }

    public void editTaskInTaskList(String taskInfo) {
        int taskIndex = -1;
        String[] command = taskInfo.split(" ", 2);
        for (Task nextTask : taskList) {
            if (nextTask.getId() == Integer.parseInt(command[0])) taskIndex = taskList.indexOf(nextTask);
        }
        if (taskIndex < 0) System.out.println(taskNotFound);
        else this.taskList.get(taskIndex).setDescription(command[1]);
    }

    public void changeTaskStatus(int taskId) {
        int taskIndex = -1;
        for (Task nextTask : taskList) {
            if (nextTask.getId() == taskId) taskIndex = taskList.indexOf(nextTask);
        }
        if (taskIndex < 0) System.out.println(taskNotFound);
            else this.taskList.get(taskIndex).setStatus(!this.taskList.get(taskIndex).getStatus());
    }

    public void deleteTaskInTaskList(int taskId) {
        int taskIndex = -1;
        for (Task nextTask : taskList) {
            if (nextTask.getId() == taskId) taskIndex = taskList.indexOf(nextTask);
        }
        if (taskIndex < 0) System.out.println(taskNotFound);
            else this.taskList.remove(this.taskList.get(taskIndex));
    }

    public int getTaskListSize(boolean onlyOpen) {
        if(onlyOpen) {
            ArrayList<Task> openTaskList = new ArrayList<>();
            for (Task taskList : taskList) {
                if (!taskList.getStatus())
                openTaskList.add(taskList);
            }
            return openTaskList.size();
        }
        return taskList.size();
    }
}
