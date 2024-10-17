package org.example;

import org.example.handlers.BookHandler;
import org.example.interfaces.InputProvider;
import org.example.model.Book;


public class App {
    public static Book[] books = new Book[100];

    private final BookHandler bookHandler;
    private final InputProvider inputProvider;

    // Constructor accepting BookHandler and InputProvider
    public App(BookHandler bookHandler, InputProvider inputProvider) {
        this.bookHandler = bookHandler;
        this.inputProvider = inputProvider;
    }

    // Main method to run the application
    public void run() {
        System.out.println("DAROVA");
        boolean repeat = true;
        do {
            // Печатаем меню
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Вывести все книги");
            System.out.println("3. Поиск книги по названию");
            System.out.print("Введите номер задачи: ");

            int task = Integer.parseInt(inputProvider.getInput());

            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    bookHandler.addBook();
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    bookHandler.listBooks(); // Выводим все книги
                    break;
                case 3:
                    System.out.println("----- Поиск книги -----");
                    bookHandler.findBookByTitle(); // Ищем книгу по названию
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
                    break;
            }
        } while (repeat);

        // Print exit message
        System.out.println("До свидания :)");
    }
}