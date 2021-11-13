package com.example.parsers;

import com.example.data.models.CommandLine;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Parser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("\\s*(?<name>\\w+)(?:\\s+(?<argument>(?:(?<id>\\d+)\\b)?(?<description>.*)))?");

    public CommandLine parseLine(String inputLine) throws NumberFormatException {
        log.debug(inputLine);
        Matcher matcher = COMMAND_TEMPLATE.matcher(inputLine);
        if (matcher.find()) {
            CommandLine.CommandLineBuilder builder = CommandLine.builder()
                    .name(matcher.group("name"))
                    .argument(matcher.group("argument"))
                    .description(matcher.group("description"));
            String taskId = matcher.group("id");
            if(taskId != null) {
                builder.taskId(Long.parseLong(taskId));
            }
            return builder.build();
        }
        return null;
    }
}
