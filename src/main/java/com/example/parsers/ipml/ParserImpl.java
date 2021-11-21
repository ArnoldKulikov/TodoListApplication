package com.example.parsers.ipml;

import com.example.parsers.CommandLine;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ParserImpl implements Parser {

    private static final Pattern COMMAND_TEMPLATE = Pattern.compile("\\s*(?<name>\\w+)(?:\\s+(?<argument>(?:(?<id>\\d+)\\b)?(?<description>.*)))?");

    public CommandLine parseLine(String inputLine) throws NumberFormatException {
        CommandLine commandLine = new CommandLine();
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
            return commandLine
                    .setName(name)
                    .setArgument(argument)
                    .setDescription(description)
                    .setTaskId(taskId);
        }
        return commandLine
                .setName("unknownCommand")
                .setArgument(null)
                .setDescription(null)
                .setTaskId(null);
    }
}
