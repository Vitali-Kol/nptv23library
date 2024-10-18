package org.example;

import org.example.handlers.BookHandler;
import org.example.handlers.ReaderHandler;
import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.interfaces.ReaderProvider;
import org.example.interfaces.impl.ConsoleInput;
import org.example.interfaces.impl.AppBookHelper;
import org.example.interfaces.impl.AppReaderHelper;

public class NPTV23Library {
    public static void main(String[] args) {
        Input inputProvider = new ConsoleInput();
        BookProvider bookProvider = new AppBookHelper();
        ReaderProvider readerProvider = new AppReaderHelper();

        BookHandler bookHandler = new BookHandler(inputProvider, bookProvider);
        ReaderHandler readerHandler = new ReaderHandler(inputProvider, readerProvider);

        System.out.println("-------------NPTV23Library-------------");
        App app = new App(bookHandler, readerHandler, inputProvider);  // Добавьте ReaderHandler в App
        app.run();  // Запуск приложения
    }
}
