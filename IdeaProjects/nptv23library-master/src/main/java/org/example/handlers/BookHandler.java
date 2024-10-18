package org.example.handlers;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.model.Autor;
import org.example.model.Book; // Убедитесь, что импорт именно из этого пакета

public class BookHandler {
    private final Input input;
    private final BookProvider bookProvider;

    public BookHandler(Input input, BookProvider bookProvider) {
        this.input = input;
        this.bookProvider = bookProvider;
    }

    public void addBook() {
        Book book = bookProvider.createBook(input);

        for (int i = 0; i < App.books.length; i++) {
            if (App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга успешно добавлена.");
                return;
            }
        }

        System.out.println("Нет свободного места для добавления новой книги.");
    }

    public void listBooks() {
        boolean hasBooks = false;
        for (Book book : App.books) {
            if (book != null) {
                System.out.println("Название книги: " + book.getTitle());
                System.out.println("Год публикации: " + book.getPublishedYear());

                System.out.println("Авторы:");
                for (Autor author : book.getAuthors()) {
                    if (author != null) {
                        System.out.println(" Имя: " + author.getName() + ", Фамилия: " + author.getSurname());
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
        String title = input.getInput();
        boolean found = false;

        for (Book book : App.books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Книга найдена!");
                System.out.println("Название: " + book.getTitle());
                System.out.println("Год публикации: " + book.getPublishedYear());

                System.out.println("Авторы:");
                for (Autor author : book.getAuthors()) {
                    if (author != null) {
                        System.out.println(" Имя: " + author.getName() + ", Фамилия: " + author.getSurname());
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
