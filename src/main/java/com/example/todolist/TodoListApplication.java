package com.example.todolist;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TodoListApplication {

	public static void main(String[] args) {

		TaskList taskList = new TaskList();
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		String commandNotFound = "\nНекорректная команда";

		while (true)
		{
			try {
				String[] command = scan.readLine().split(" ",2);
				switch(command[0]) {
					case "add":
						taskList.addTask(command[1]);
						break;

					case "print":
						if(command.length == 1) {
							taskList.printTaskList(true);
							break;
						}
						if(command[1].equals("all")) {
							taskList.printTaskList(false);
							break;
						}
						System.out.println(commandNotFound);
						break;

					case "search":
						taskList.searchTaskByDescription(command[1]);
						break;

					case "toggle":
						taskList.changeTaskStatus(Integer.parseInt(command[1]));
						break;

					case "delete":
						taskList.deleteTaskInTaskList(Integer.parseInt(command[1]));
						break;

					case "edit":
						taskList.editTaskInTaskList(command[1]);
						break;

					case "quit":
						return;

					default:
						System.out.println(commandNotFound);
				}
			}

			catch (Exception ex) {
				System.out.println(commandNotFound);
			}
		}



	}
}
