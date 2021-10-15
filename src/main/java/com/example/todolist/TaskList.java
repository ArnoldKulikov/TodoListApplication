package com.example.todolist;

import java.util.ArrayList;

public class TaskList { // Класс для работы со списком задач

    private String name; // Название списка задач
    private ArrayList<Task> taskList = new ArrayList(); // Список задач типа Task

    public TaskList(String name) { // Конструктор для создания Списка задач
        this.name = name; // Устанавливаем имя на полученное
    }

    public void setTaskList(String task) { // Добавление задачи в список задач
        Task receivedTask = new Task(this.taskList.size() + 1, false, task); // Создаем новую задачу
        this.taskList.add(receivedTask); // Добавляем задачу в список
    }

    public void printTaskList(boolean onlyOpen) { // Выводим на консоль список задач в зависимости от полученного на вход флага
        if (this.getTaskListSize(onlyOpen) == 0) // Проверям что в списке задач есть открытые
            System.out.println(onlyOpen?"\nСписок открытых задач пуст":"\nСписок задач пуст"); // Сообщаем что открытых задач нет
        else {
            System.out.println(this.name); // Выводим название списка задач
            if (onlyOpen) { // Проверка флага
                for (Task taskList : taskList) { // Для каждой задачи выполняем проверку
                    if (!taskList.getStatus()) // Что задача открыта
                        System.out.println(taskList); // Выводим открытую задачу
                }
                return; // Останавливаем вывод списка открытых задач
            }
            for (Task taskList : taskList) { // Для каждой задачи
                System.out.println(taskList); // Выводим задачу
            }
        }
    }

    public void changeTaskStatus(int number) { // Переключение статуса задачи
        this.taskList.get(number-1).setStatus(!this.taskList.get(number-1).getStatus()); // Берем текущий статус и меняем на противоположный
        System.out.println("\nТекущий статус задачи:"); // Сообщение о смене статуса
        System.out.println(taskList.get(number-1)); // Выводм измененную задачу
    }

    public int getTaskListSize(boolean onlyOpen) {  // Получение размера списка задач в зависимости от полученного на вход флага
        if(onlyOpen) { // Проверка флага
            ArrayList<Task> openTaskList = new ArrayList(); // Создаем временный массив только для открытых задач
            for (Task taskList : taskList) { // Для каждй задачи выполняем проверку
                if (!taskList.getStatus()) // Что задача открыта
                openTaskList.add(taskList); // Добавляем открытую задачу во временный список
            }
            return openTaskList.size(); // Возвращаем размер списка открытых задач
        }
        return taskList.size(); // Возвращаем размер списка всех задач
    }
}
