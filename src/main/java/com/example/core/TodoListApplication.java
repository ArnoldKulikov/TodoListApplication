package com.example.core;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TodoListApplication {

	public static void main(String[] args) {

		log.info("Программа запущена");

		CommandProcessor processor = new CommandProcessor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try {
				processor.executeCommand(reader.readLine());
			}
			catch (IOException | MyException e) {
				System.out.println(e.getMessage());
				log.error(e.getMessage());
			}

		}
	}
}
