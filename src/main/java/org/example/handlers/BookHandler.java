package org.example.handlers;

import org.example.interfaces.InputProvider;
import org.example.interfaces.BookProvider;
import org.example.model.Book;

public class BookHandler {
    private final InputProvider inputProvider;
    private final BookProvider bookProvider;

    public BookHandler(InputProvider inputProvider, BookProvider bookProvider) {
        this.inputProvider = inputProvider;
        this.bookProvider = bookProvider; // Убедитесь, что bookProvider инициализирован
    }

    public void addBook() {
        // Реализация добавления книги
        // Используйте bookProvider для создания книги
        Book newBook = bookProvider.createBook(inputProvider);
        // Логика добавления новой книги
    }
}
