package org.example;

import java.util.Scanner;

public class App {
    private Scanner scanner;
    private BookService bookService;

    public App() {
        this.scanner = new Scanner(System.in);
        this.bookService = new BookService(); // Инициализация BookService
    }

    public void run() {
        int task = -1;
        while (task != 0) {
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Показать все книги");
            System.out.print("Введите номер задачи: ");

            if (scanner.hasNextInt()) {
                task = scanner.nextInt();
                scanner.nextLine(); // очистка переноса строки

                switch (task) {
                    case 0:
                        System.out.println("Выход из программы...");
                        break;
                    case 1:
                        addBook(); // Вызов метода добавления книги
                        break;
                    case 2:
                        showBooks(); // Вызов метода показа книг
                        break;
                    default:
                        System.out.println("Выберите задачу из списка задач");
                }
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.nextLine(); // очистка некорректного ввода
            }
        }
        scanner.close(); // Закрытие сканера для освобождения ресурсов
    }

    private void addBook() {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите год публикации: ");
        int publishedYear = scanner.nextInt();
        scanner.nextLine(); // очистка переноса строки

        System.out.print("Введите количество авторов: ");
        int authorCount = scanner.nextInt();
        scanner.nextLine(); // очистка переноса строки

        Author[] authors = new Author[authorCount];
        for (int i = 0; i < authorCount; i++) {
            System.out.print("Введите имя автора " + (i + 1) + ": ");
            String authorName = scanner.nextLine();
            authors[i] = new Author(authorName); // Создание объекта Author
        }

        bookService.addBook(title, authors, publishedYear);
    }

    private void showBooks() {
        System.out.println("Список книг:");
        for (Book book : bookService.getBooks()) {
            System.out.println(book);
        }
    }
}
