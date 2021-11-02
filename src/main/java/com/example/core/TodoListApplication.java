package com.example.core;

import com.example.dictionaries.ErrorList;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TodoListApplication {

	public static void main(String[] args) {

		//if (args[0].equals("debug") || args[0].isEmpty()) System.setProperty("LEVEL", args[0].equals("debug")?args[0]:"info");

		log.info("Программа запущена");

		CommandProcessor processor = new CommandProcessor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try {
				processor.executeCommand(reader.readLine());
			}
			catch (IOException e) {
				log.error(ErrorList.ERRORLIST.get("unknownError"));
			}

		}
	}
}
