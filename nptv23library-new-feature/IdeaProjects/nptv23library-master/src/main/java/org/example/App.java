package org.example;

import org.example.handlers.BookHandler;
import org.example.handlers.ReaderHandler;
import org.example.interfaces.Input;
import org.example.model.Book;
import org.example.model.Reader;

public class App {
    public static Book[] books = new Book[100];
    public static Reader[] readers = new Reader[100];

    private final BookHandler bookHandler;
    private final ReaderHandler readerHandler; // Добавляем менеджер читателей
    private final Input input;

    public App(BookHandler bookHandler, ReaderHandler readerHandler, Input input) {
        this.bookHandler = bookHandler;
        this.readerHandler = readerHandler; // Инициализация менеджера читателей
        this.input = input;
    }

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
            System.out.println("4. Зарегистрировать читателя"); // Новая задача
            System.out.println("5. Вывести всех читателей"); // Новая задача
            System.out.print("Введите номер задачи: ");

            int task = Integer.parseInt(input.getInput());

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
                    bookHandler.listBooks();
                    break;
                case 3:
                    System.out.println("----- Поиск книги -----");
                    bookHandler.findBookByTitle();
                    break;
                case 4:
                    System.out.println("----- Регистрация читателя -----");
                    readerHandler.addReader(); // Регистрация нового читателя
                    break;
                case 5:
                    System.out.println("----- Список читателей -----");
                    readerHandler.listReaders(); // Вывод всех читателей
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
                    break;
            }
        } while (repeat);

        System.out.println("До свидания :)");
    }
}
