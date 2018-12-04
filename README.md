Группа 2018-08

Pavel Ezhkov (Павел Ежков)

pachaejk@gmail.com

Домашние задания:

ДЗ 01. Сборка и запуск проекта

Создать проект под управлением maven в Intellij IDEA. 

Добавить зависимость на Google Guava/Apache Commons/библиотеку на ваш выбор.
Использовать библиотечные классы для обработки входных данных.

Задать имя проекта (project_name) в pom.xml 
Собрать project_name.jar содержащий все зависимости.
Проверить, что приложение можно запустить из командной строки.

Выложить проект на github.

ДЗ 02. Измерение памяти

Написать стенд для определения размера объекта. Определить размер пустой строки и пустых контейнеров. Определить рост размера контейнера от количества элементов в нем.


Например:
Object — 8 bytes,
Empty String — 40 bytes
Array — from 12 bytes

ДЗ 03. MyArrayList

Написать свою реализацию ArrayList на основе массива. Проверить, что на ней работают методы java.util.Collections

ДЗ 04. Тестовый фреймворк на аннотациях

Написать свой тестовый фреймворк. Поддержать аннотации @Test, @Before, @After. 
Запускать вызовом статического метода с 
1. именем класса с тестами, 
2. именем package в котором надо найти и запустить тесты

ДЗ 05. Измерение активности GC

Написать приложение, которое следит за сборками мусора и пишет в лог количество сборок каждого типа (young, old) и время которое ушло на сборки в минуту.
Добиться OutOfMemory в этом приложении через медленное подтекание по памяти (например добавлять элементы в List и удалять только половину).
Настроить приложение (можно добавлять Thread.sleep(...)) так чтобы оно падало с OOM примерно через 5 минут после начала работы.
Собрать статистику (количество сборок, время на сборрки) по разным типам GC.

ДЗ-06: my cache engine

Напишите свой cache engine с soft references

ДЗ 07. Написать эмулятор АТМ

Написать эмулятор АТМ (банкомата).

Объект класса АТМ должен уметь
• принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
• выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать
• выдавать сумму остатка денежных средств

ДЗ 08. ATM Department

Написать приложение ATM Department:
• Приложение может содержать несколько ATM
• Departmant может собирать сумму остатков со всех ATM
• Department может инициировать событие – восстановить состояние всех ATM до начального.
(начальные состояния у разных ATM могут быть разными)

ДЗ-09: JSON object writer

Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json или simple-json и Reflection.
Поддержите массивы объектов и примитивных типов, и коллекции из стандартный библиотерки.

ДЗ-10: myORM

Создайте в базе таблицу с полями: 
id bigint(20) NOT NULL auto_increment 
name varchar(255)
age int(3)

Создайте абстрактный класс DataSet. Поместите long id в DataSet. 
Добавьте класс UserDataSet (с полями, которые соответствуют таблице) унаследуйте его от DataSet. 

Напишите Executor, который сохраняет наследников DataSet в базу и читает их из базы по id и классу. 

<T extends DataSet> void save(T user){…}
<T extends DataSet> T load(long id, Class<T> clazz){…}
