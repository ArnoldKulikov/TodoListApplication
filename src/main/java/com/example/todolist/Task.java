package com.example.todolist;

public class Task {
    private int number; // Номер задачи
    private boolean isClosed; // Статус задачи, по умолчанию false, true - если закрыта
    private String description; // Описание задачи

    public Task(int number, boolean isClosed, String description) { // Конструктор для создания новой задачи
        this.number = number; // Номер задачи устанавливаем на полученный
        this.isClosed = isClosed; // Статус задачи устанавливаем на полученный
        this.description = description; // Описание задачи устанавливаем на полученное
    }

    public boolean getStatus() {
        return isClosed;
    }

    public void setStatus(boolean newstatus) {
        isClosed = newstatus;
    }

    @Override
    public String toString() { // Вывод задачи строкой
        return  number + ". " + (isClosed?"[x]":"[ ]") + " " + description; // Вывод задачи строкой в нужном формате
    }
}
