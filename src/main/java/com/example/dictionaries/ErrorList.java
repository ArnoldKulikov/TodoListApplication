package com.example.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class ErrorList {

    public static final HashMap<String, String> ERRORLIST = new HashMap<>(Map.of(
            "unknownCommand", "Некорректная команда",
            "taskNotFound", "Задача не найдена",
            "unknownError", "Неизвестная ошибка",
            "unknownSubCommand", "Некорректный аргумент команды"));
}
