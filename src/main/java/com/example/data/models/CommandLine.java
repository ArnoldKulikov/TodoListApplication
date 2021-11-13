package com.example.data.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommandLine {

    private final String name;
    private final Long taskId;
    private final String description;
    private final String argument;

}
