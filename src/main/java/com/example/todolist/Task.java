package com.example.todolist;

public class Task {
    private int id; // Номер задачи
    private boolean isClosed; // Статус задачи, по умолчанию false, true - если закрыта
    private String description; // Описание задачи

    public Task(int id, boolean isClosed, String description) { // Конструктор для создания новой задачи
        this.id = id; // Номер задачи устанавливаем на полученный
        this.isClosed = isClosed; // Статус задачи устанавливаем на полученный
        this.description = description; // Описание задачи устанавливаем на полученное
    }

    public boolean getStatus() {
        return isClosed;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setStatus(boolean newStatus) {
        isClosed = newStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() { // Вывод задачи строкой
        return  id + ". " + (isClosed?"[x]":"[ ]") + " " + description; // Вывод задачи строкой в нужном формате
    }
}
