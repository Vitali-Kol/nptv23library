package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void testRunExit() {
        String input = "0\n"; // Ввод для выхода из программы
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        String output = outContent.toString();
        assertTrue(output.contains("Выход из программы")); // Проверяем, что программа завершилась корректно
    }

    @Test
    public void testRunInvalidTask() {
        String input = "2\n0\n"; // Ввод для неверной задачи и выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        String output = outContent.toString();
        assertTrue(output.contains("Выберите задачу из списка задач")); // Проверка неверного ввода
        assertTrue(output.contains("Выход из программы")); // Проверка корректного завершения
    }

    @Test
    public void testRunValidTask() {
        String input = "1\n0\n"; // Ввод для задачи и выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        String output = outContent.toString();
        assertTrue(output.contains("--Добавление книги--")); // Проверка вывода задачи
        assertTrue(output.contains("Выход из программы")); // Проверка завершения
    }
}
