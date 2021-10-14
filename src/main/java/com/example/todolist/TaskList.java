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
        System.out.println(this.name); // Выводим название списка задач
        if(onlyOpen) { // Проверка флага
            for (Task taskList : taskList) { // Для каждой задачи выполняем проверку
                if (!taskList.isClosed) // Что задача открыта
                System.out.println(taskList); // Выводим открытую задачу
            }
            return; // Останавливаем вывод списка открытых задач
        }
        for (Task taskList : taskList) { // Для каждой задачи
            System.out.println(taskList); // Выводим задачу
        }
    }

    public void changeTaskStatus(int number) { // Переключение статуса задачи
        this.taskList.get(number-1).isClosed = !this.taskList.get(number-1).isClosed; // Берем текущий статус и меняем на противоположный
        System.out.println("\nТекущий статус задачи:"); // Сообщение о смене статуса
        System.out.println(taskList.get(number-1)); // Выводм измененную задачу
    }

    public int getTaskListSize(boolean onlyOpen) {  // Получение размера списка задач в зависимости от полученного на вход флага
        if(onlyOpen) { // Проверка флага
            ArrayList<Task> openTaskList = new ArrayList(); // Создаем временный массив только для открытых задач
            for (Task taskList : taskList) { // Для каждй задачи выполняем проверку
                if (!taskList.isClosed) // Что задача открыта
                openTaskList.add(taskList); // Добавляем открытую задачу во временный список
            }
            return openTaskList.size(); // Возвращаем размер списка открытых задач
        }
        return taskList.size(); // Возвращаем размер списка всех задач
    }

    private static class Task { // Класс для описания задачи
        private int number; // Номер задачи
        private boolean isClosed; // Статус задачи, по умолчанию false, true - если закрыта
        private String description; // Описание задачи

        public Task(int number, boolean isClosed, String description) { // Конструктор для создания новой задачи
            this.number = number; // Номер задачи устанавливаем на полученный
            this.isClosed = isClosed; // Статус задачи устанавливаем на полученный
            this.description = description; // Описание задачи устанавливаем на полученное
        }

        @Override
        public String toString() { // Вывод задачи строкой
            return  number + ". " + (isClosed?"[x]":"[ ]") + " " + description; // Вывод задачи строкой в нужном формате
        }
    }
}
