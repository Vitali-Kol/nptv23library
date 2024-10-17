package org.example.interfaces.impl;

import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Autor;
import org.example.model.Book;

public class AppBookHelper implements BookProvider {

    @Override
    public Book createBook(InputProvider inputProvider) {
        // Создаем новую книгу
        Book book = new Book();

        // Вводим название книги
        System.out.print("Введите название книги: ");
        book.setTitle(inputProvider.getInput());

        // Вводим количество авторов
        System.out.print("Количество авторов: ");
        int countAuthors = Integer.parseInt(inputProvider.getInput());

        // Массив для авторов
        Autor[] authors = new Autor[countAuthors];

        // Вводим информацию о каждом авторе
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("(" + (i + 1) + ") автор: ");

            // Создаем автора
            Autor author = new Autor();

            // Вводим имя автора
            System.out.print("Имя автора: ");
            author.setName(inputProvider.getInput());

            // Вводим фамилию автора
            System.out.print("Фамилия автора: ");
            author.setSurname(inputProvider.getInput());

            // Добавляем автора в массив
            authors[i] = author;
        }

        // Устанавливаем массив авторов в книгу
        book.setAuthors(authors);

        // Вводим год публикации
        System.out.print("Введите год публикации книги: ");
        int year = Integer.parseInt(inputProvider.getInput());  // Читаем год публикации
        book.setPublishedYear(year);  // Устанавливаем год публикации

        // Возвращаем созданную книгу
        return book;
    }
}