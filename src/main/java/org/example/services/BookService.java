package org.example.services;


import org.example.model.Autor;
import org.example.model.Book;

public class BookService {
    public Book     createBook() {
        Autor autor = new Autor("Лев","Толстой");
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setPublishedYear(2000);
        book.getAuthors()[0]= autor;
        return book;
    }
}