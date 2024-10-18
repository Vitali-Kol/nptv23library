package org.example.handlers;

import org.example.App;
import org.example.interfaces.Input;
import org.example.interfaces.ReaderProvider;
import org.example.model.Reader;

public class ReaderHandler {
    private final Input inputProvider;
    private final ReaderProvider readerProvider;

    // Constructor accepting InputProvider and ReaderProvider
    public ReaderHandler(Input inputProvider, ReaderProvider readerProvider) {
        this.inputProvider = inputProvider;
        this.readerProvider = readerProvider;
    }

    // Method to add a reader
    public void addReader() {
        Reader reader = readerProvider.createReader(inputProvider);  // Create a new reader using input

        // Loop through the App.readers array to find an available spot
        for (int i = 0; i < App.readers.length; i++) {
            if (App.readers[i] == null) {
                App.readers[i] = reader;
                break;
            }
        }
    }

    // Method to list all readers
    public void listReaders() {
        boolean hasReaders = false; // Флаг для проверки наличия читателей
        for (Reader reader : App.readers) {
            if (reader != null) {
                System.out.println("Имя читателя: " + reader.getName());
                System.out.println("Фамилия читателя: " + reader.getSurname());
                System.out.println("Телефон читателя: +372 " + reader.getPhone());
                System.out.println("------------------------");
                hasReaders = true;
            }
        }

        if (!hasReaders) {
            System.out.println("В системе нет зарегистрированных читателей.");
        }
    }
}
