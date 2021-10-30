package com.example.core;

import com.example.dictionaries.ErrorList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoListApplication {

	public static void main(String[] args) {

		if (args[0].equals("debug") || args[0].isEmpty()) System.setProperty("LEVEL", args[0].equals("debug")?args[0]:"info");

		Logback.logger.info("Программа запущена");

		TaskProcessor processor = new TaskProcessor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try {
				processor.executeCommand(reader.readLine());
			}
			catch (IOException e) {
				Logback.logger.error(ErrorList.ERRORLIST.get("unknownError"));
			}

		}
	}
}
