package com.example.todolist;

import java.util.ArrayList;

public class TaskList { // Класс для работы со списком задач

    private String name; // Название списка задач
    private Task taskList; // Список задач типа Task в виде одной записи

    public TaskList(String name) { // Конструктор для создания Списка задач
        this.name = name; // Устанавливаем имя на полученное
    }

    public void setTaskList(String task) { // Добавление задачи в список задач
        taskList = new Task(1, false, task); // Создаем новую задачу заменяя старую при наличии, статус не сохраняется
    }

    public void printTaskList(boolean onlyOpen) { // Выводим на консоль список задач в зависимости от полученного на вход флага
        if (this.getTaskListSize(onlyOpen) == 0) // Проверям что в списке задач есть садачи с нужным статусом
            System.out.println(onlyOpen?"\nСписок открытых задач пуст":"\nСписок задач пуст"); // Сообщаем что задач нет
        else {
            System.out.println(this.name + "\n" + taskList); // Выводим название списка задач и список задач
        }
    }

    public void changeTaskStatus(int number) { // Переключение статуса задачи
        this.taskList.setStatus(!this.taskList.getStatus()); // Берем текущий статус и меняем на противоположный
        System.out.println("\nТекущий статус задачи:\n" + taskList); // Сообщение о смене статуса и выводим задачу
    }

    public int getTaskListSize(boolean onlyOpen) {  // Получение размера списка задач в зависимости от полученного на вход флага
        if(onlyOpen) { // Проверка флага
            return this.taskList == null || this.taskList.getStatus()?0:1; // Возвращаем размер списка открытых задач
        }
        return this.taskList == null?0:1; // Возвращаем размер списка всех задач
    }
}
