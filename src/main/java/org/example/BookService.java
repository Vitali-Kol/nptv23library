package org.example;

public class BookService {
    public void createBook(Book book) {
        Author author = new Author("лев", "Толстой");
        Book book = new Book();
        book.setTitle("война и мир");
        book.setPublishedYear(2000);
        book.getAuthors()[0] = author;
        return book;
    }

    public void addBook(String title, Author[] authors, int publishedYear) {
    }
}

