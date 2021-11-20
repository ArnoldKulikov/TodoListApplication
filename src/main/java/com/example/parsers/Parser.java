package com.example.parsers;

import com.example.parsers.CommandLine;

public interface Parser {

    CommandLine parseLine(String inputLine) throws NumberFormatException;
}
