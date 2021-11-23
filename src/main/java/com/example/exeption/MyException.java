package com.example.exeption;

import java.util.Map;

public class MyException extends Exception {

    private static final Map<String, String> errorList = Map.of(
            "unknownCommand", "Некорректная команда",
            "taskNotFound", "Задача не найдена",
            "unknownError", "Неизвестная ошибка",
            "unknownSubCommand", "Некорректный аргумент команды",
            "notTaskId", "Введен некорректный идентификатор задачи",
            "emptyTaskDescription", "Пустое описание задачи");

    public MyException(String message) {
        super(errorList.get(message));
    }
}
