package org.example.handlers;

import org.example.App;
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
        Book book = bookProvider.createBook(inputProvider);  // Create a new book using input

        // Loop through the App.books array to find an available spot
        for (int i = 0; i < App.books.length; i++) {
            if (i == 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("книга добавлена");
                break;
            } else if (i > 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("книга добавлена");
                break;
            }
        }
    }
}
