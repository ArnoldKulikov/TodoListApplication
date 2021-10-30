package com.example.dictionaries;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SettingsList {
    HashMap<String, Integer> settingsList = new HashMap<>(Map.of(
            "nextTaskId", 1));
}
