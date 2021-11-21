package com.example.data.dictionaries;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ErrorList {

    private final Map<String, String> errorList = Map.of(
            "unknownCommand", "Некорректная команда",
            "taskNotFound", "Задача не найдена",
            "unknownError", "Неизвестная ошибка",
            "unknownSubCommand", "Некорректный аргумент команды",
            "notTaskId", "Введен некорректный идентификатор задачи",
            "emptyTaskDescription", "Пустое описание задачи");

    public Map<String, String> getErrorList() {
        return errorList;
    }
}
