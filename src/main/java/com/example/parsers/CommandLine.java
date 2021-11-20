package com.example.parsers;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Accessors(chain = true)
@Component
public class CommandLine {

    private String name;
    private Long taskId;
    private String description;
    private String argument;

}
