# Приложение для работы со списком задач
## Описание поддерживаемых команд
### add
Формат команды: add <описание задачи>

Описание задачи может содержать любые символы, кроме перевода строки. Перевод строки (нажатие клавиши Enter) означает завершение ввода описания задачи

### print

Формат команды: print [all]

Выводит на печать список задач. По-умолчанию, выводятся только невыполненные задачи, в случае если команда выполняется с аргументом all - печатаются все задачи. Печатаются следующие поля: идентификатор, который используется в команде toggle, статус задачи (x - выполнена, " " - не выполнена), описание задачи.

Пример вывода:

1. [x] Реализовать сборку на maven
2. [ ] Реализовать сборку на gradle

### search

Формат команды: search <substring>

Выводит на печать список задач, описание которых содержит substring. Вывод на печать в формате аналогичном команде print.

Пример вывода:

3. [x] Реализовать сборку на maven
8. [ ] Реализовать сборку на gradle


### toggle

Формат команды: toggle <идентификатор задачи>

Переключает состояние задачи (с "выполнена" на "не выполнена" и наоборот) по идентификатору. 


### delete

Формат команды: delete <идентификатор задачи>

Удаляет задачу из списка задач.


### edit

Формат команды: edit <идентификатор задачи> <новое значение>

Меняет описание задачи. 


### quit

Формат команды: quit

Завершает работу программы

## Описание логирования

### Параметры логирования

В приложении поддерживаются уровни логирования. Для указания нужного уровня логирования нужно задать системный параметр java -DLEVEL при старте программы. По умолчанию установлен уровень логирования debug.

### Сообщения делятся на категории

#### IFNO

Важные действия в приложении. Это не ошибки и не предостережение, это ожидаемые действия системы.

#### DEBUG

Сообщения необходимые для отладки приложения. Для уверенности в том, что система делает именно то, что от нее ожидают.

#### ERROR

Сообщения об ошибках, которые нужно решить. Ошибка не останавливает работу приложения в целом. Остальные запросы могут работать корректно.