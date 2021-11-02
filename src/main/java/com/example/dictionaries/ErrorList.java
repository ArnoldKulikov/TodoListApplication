package com.example.dictionaries;


import java.util.Map;

public class ErrorList {

    public static final Map<String, String> ERRORLIST = Map.of(
            "unknownCommand", "Некорректная команда",
            "taskNotFound", "Задача не найдена",
            "unknownError", "Неизвестная ошибка",
            "unknownSubCommand", "Некорректный аргумент команды",
            "notTaskId", "Введен некорректный идентификатор задачи");
}
