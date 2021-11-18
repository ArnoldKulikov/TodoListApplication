package com.example.parsers;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CommandLine {

    private String name;
    private Long taskId;
    private String description;
    private String argument;

}
