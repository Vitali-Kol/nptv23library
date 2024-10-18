package org.example.interfaces.impl;

import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.model.Autor;
import org.example.model.Book;

public class AppBookHelper implements BookProvider {

    @Override
    public Book createBook(Input input) {
        // Создаем новую книгу
        Book book = new Book();

        // Вводим название книги
        System.out.print("Введите название книги: ");
        book.setTitle(input.getInput());

        // Вводим количество авторов
        System.out.print("Количество авторов: ");
        int countAuthors = Integer.parseInt(input.getInput());

        // Массив для авторов
        Autor[] authors = new Autor[countAuthors];

        // Вводим информацию о каждом авторе
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("(" + (i + 1) + ") автор: ");

            // Создаем автора
            Autor author = new Autor();

            // Вводим имя автора
            System.out.print("Имя автора: ");
            author.setName(input.getInput());

            // Вводим фамилию автора
            System.out.print("Фамилия автора: ");
            author.setSurname(input.getInput());

            // Добавляем автора в массив
            authors[i] = author;
        }

        // Устанавливаем массив авторов в книгу
        book.setAuthors(authors);

        // Вводим год публикации
        System.out.print("Введите год публикации книги: ");
        int year = Integer.parseInt(input.getInput());  // Читаем год публикации
        book.setPublishedYear(year);  // Устанавливаем год публикации

        // Возвращаем созданную книгу
        return book;
    }
}