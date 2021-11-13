package com.example.parsers;

import com.example.data.models.CommandLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("\\s*(?<name>\\w+)(?:\\s+(?<argument>(?:(?<id>\\d+)\\b)?(?<description>.*)))?");

    public CommandLine parseLine(String inputLine) throws NumberFormatException {
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
