package com.example.parsers;

public interface Parser {

    CommandLine parseLine(String inputLine) throws NumberFormatException;
}
