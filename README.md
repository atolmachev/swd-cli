Simple Shell
============

Часть I (08.09.2015)
--------------------


Разработать простой shell, представляющий собой REPL (read - evaluate - print loop),
поддерживающий следующие команды
 - cat [FILE]
 - wc [FILE]
 - pwd
 - exit
 - man [COMMAND]

Примеры

    > cat README.md
    I dont't know md syntax, so I'll left this file blank

    > wc README.md
    1 11 54

    > man cat
    cat - prints file to standard output
    cat [FILE]

Интерпретатор должен поддерживать piplines:

    > cat README.md | wc

Дизайн программы должен позволять добавление новых команд (например, другими разработчиками)
Используем JDK 8
Сделанное задание = репозиторий на github с кодом и файлом проекта (IDEA, Eclipse) с Runtime - конфигурацией для запуска
Программа дожна запусаться на любой машине с установленной JDK

Часть II (15.09.2015)
---------------------

Реализовать команду grep на базе следующих библиотек
- [Apache Commons CLI] (https://commons.apache.org/proper/commons-cli/)
- [JCommander] (https://github.com/cbeust/jcommander)
- Библиотеки соседа из задания I (делаете fork и не меняя классов дописываете нужные для реализации команды)

!Если API соседа не устраивает/содержит баги/... можно и нужно сообщить об этом соседу.

Минимальный синтаксис grep:
    > grep "Минимальный" README.md
    Минимальный синтаксис grep

    > grep "Минимальный$" README.md

    > grep "^Минимальный" README.md
    Минимальный синтаксис grep

    > grep -i "минимальный" README.md
    Минимальный синтаксис grep

    > grep -w "Минимал" README.md

    > grep -A 1 "II" README.md
    Часть II
    --------

Сделанное задание = реализации grep на Apache Cli + на JCommander + на библиотеке товарища + короткий ответ на вопрос "Что в API товарища вам понравилось, а что вы бы улучшили?"