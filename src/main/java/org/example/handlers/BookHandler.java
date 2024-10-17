package org.example.handlers;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Autor;
import org.example.model.Book;

public class BookHandler {
    private final InputProvider inputProvider;
    private final BookProvider bookProvider;

    // Constructor accepting InputProvider and BookProvider
    public BookHandler(InputProvider inputProvider, BookProvider bookProvider) {
        this.inputProvider = inputProvider;
        this.bookProvider = bookProvider;
    }

    // Method to add a book
    public void addBook() {
        Book book = bookProvider.createBook(inputProvider);  // Create a new book using input

        // Loop through the App.books array to find an available spot
        for (int i = 0; i < App.books.length; i++) {
            if (App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга успешно добавлена.");
                return;  // Выходим из метода, так как книга добавлена
            }
        }

        // Если нет свободного места
        System.out.println("Нет свободного места для добавления новой книги.");
    }

    public void listBooks() {
        boolean hasBooks = false; // Флаг для проверки наличия книг
        for (Book book : App.books) {
            if (book != null) {
                System.out.println("Название книги: " + book.getTitle());
                System.out.println("Год публикации: " + book.getPublishedYear());

                // Вывод авторов
                System.out.println("Авторы:");
                for (Autor author : book.getAuthors()) {
                    if (author != null) {
                        System.out.println("- Имя: " + author.getName() + ", Фамилия: " + author.getSurname());
                    }
                }

                System.out.println("------------------------");
                hasBooks = true;
            }
        }

        if (!hasBooks) {
            System.out.println("В системе нет добавленных книг.");
        }
    }

    public void findBookByTitle() {
        System.out.print("Введите название книги для поиска: ");
        String title = inputProvider.getInput(); // Получаем название от пользователя
        boolean found = false;

        for (Book book : App.books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Книга найдена!");
                System.out.println("Название: " + book.getTitle());
                System.out.println("Год публикации: " + book.getPublishedYear());

                // Вывод авторов
                System.out.println("Авторы:");
                for (Autor author : book.getAuthors()) {
                    if (author != null) {
                        System.out.println("- Имя: " + author.getName() + ", Фамилия: " + author.getSurname());
                    }
                }

                System.out.println("------------------------");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Книга с таким названием не найдена.");
        }
    }
}
