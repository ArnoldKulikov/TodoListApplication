package com.example.parsers.ipml;

import com.example.parsers.CommandLine;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ParserImpl implements Parser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("\\s*(?<name>\\w+)(?:\\s+(?<argument>(?:(?<id>\\d+)\\b)?(?<description>.*)))?");

    public CommandLine parseLine(String inputLine) throws NumberFormatException {
        log.debug(inputLine);
        Matcher matcher = COMMAND_TEMPLATE.matcher(inputLine);
        if (matcher.find()) {
            String name = matcher.group("name");
            String argument = matcher.group("argument");
            String description = matcher.group("description");
            String id = matcher.group("id");
            Long taskId = null;
            if(id != null) {
                taskId = Long.parseLong(id);
            }
            return new CommandLine()
                    .setName(name)
                    .setArgument(argument)
                    .setDescription(description)
                    .setTaskId(taskId);
        }
        return new CommandLine()
                .setName("unknownCommand");
    }
}
