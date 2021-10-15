package com.example.todolist;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TodoListApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TodoListApplication.class, args);

		TaskList taskList = new TaskList("Список моих задач:"); // Создаем объект Список задач, для работы со списком задач
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in)); // Создаем объект ридера, для чтения ввода с консоли
		String errorText = "\nНекорректная команда"; // Текст для ошибки ввода команды

		System.out.println("Программа запущена!"); // Приветствие программы

		while (true) // Непрерываемое условие для выхода по команде
		{
			System.out.println("\nВведите команду:"); // Запрос ввода команды
			try { // Пытаемся определить и обработать команду
				String[] command = scan.readLine().split(" ",2); // Получение команды и значимой части в массив из двух строк
				switch(command[0]) { // Определяем что за команда
					case "add": // Команда добавления задачи
						taskList.setTaskList(command[1]); // Передаем описание задачи для добавлеия в список
						break; // Остановка команды

					case "print": // Команда печати списка задач
						if(command.length == 1) { // Проверяем что команда передана без аргумента
							taskList.printTaskList(true);// Печатаем список открытых задач, передаем аргумент печати
							break; // Остановка команды
						}
						if(command[1].equals("all")) { // Проверяем что команда передана с правильным аргументом
							taskList.printTaskList(false);// Печатаем список задач, передаем аргумент печати
							break; // Остановка команды
						}
						System.out.println(errorText); // Выводим сообщение что команда ошибочная
						break; // Остановка команды

					case "toggle": // Команда для изменения статуса задачи на противоположный
						taskList.changeTaskStatus(Integer.parseInt(command[1])); // Меняем текущий статус задачи
						break;// Остановка команды

					case "quit": // Команда выход из программы
						System.out.println("\nПрограмма завершена"); // Сообщение о завершении программы
						return; // Завершение программы

					default: // Команда ошибочная
						System.out.println(errorText); // Выводим сообщение что команда ошибочная
				}
			}

			catch (Exception ex) { // Ловим ошибки при работе с командами
				System.out.println(errorText); // Ввывод сообщения об ошибке
			}
		}



	}
}
