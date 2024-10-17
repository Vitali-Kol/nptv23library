package org.example;

import org.example.handlers.BookHandler;
import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.interfaces.impl.ConsoleInput;
import org.example.interfaces.impl.AppBookHelper;

public class NPTV23Library {
    public static void main(String[] args) {
        InputProvider inputProvider = new ConsoleInput();  // Initialize ConsoleInput
        BookProvider bookProvider = new AppBookHelper();  // Initialize InputBook
        BookHandler bookHandler = new BookHandler(inputProvider, bookProvider);  // Pass input and book provider to handler
        System.out.println("-------------NPTV23Library-------------");
        App app = new App(bookHandler, inputProvider);  // Pass BookHandler and InputProvider to App
        app.run();  // Start the application
    }
}