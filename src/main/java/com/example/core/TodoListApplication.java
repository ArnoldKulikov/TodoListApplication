package com.example.core;

import com.example.data.models.MyException;
import com.example.parsers.Parser;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TodoListApplication {

	public static void main(String[] args) {

		log.info("Программа запущена");

		CommandProcessor processor = new CommandProcessor();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Parser parser = new Parser();

		while (true)
		{
			try {
				processor.executeCommand(parser.parseLine(bufferedReader.readLine()));
			}
			catch (IOException | MyException | NumberFormatException e) {
				System.out.println(e.getMessage());
				log.error(e.getMessage());
			}

		}
	}
}
