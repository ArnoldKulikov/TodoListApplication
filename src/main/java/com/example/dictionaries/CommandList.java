package com.example.dictionaries;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommandList {
    HashMap<String, String> commandList = new HashMap<>(Map.of(
        "add", "addCommand",
        "print", "printCommand",
        "search", "searchCommand",
        "toggle", "toggleCommand",
        "delete", "deleteCommand",
        "edit", "editCommand",
        "quit", "quitCommand"));
}
