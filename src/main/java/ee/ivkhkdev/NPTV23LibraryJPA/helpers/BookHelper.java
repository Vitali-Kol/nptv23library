package ee.ivkhkdev.NPTV23LibraryJPA.helpers;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class BookHelper {

    /**
     * Создаёт новую книгу.
     *
     * @param title  Название книги.
     * @param genre  Жанр книги.
     * @param author Автор книги.
     * @return Новый объект Book.
     */
    public static Book createBook(String title, String genre, Author author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Жанр книги не может быть пустым.");
        }
        if (author == null) {
            throw new IllegalArgumentException("Автор книги не может быть пустым.");
        }

        return new Book(title, genre, author); // Используем новый конструктор с автором
    }

    /**
     * Форматирует список книг для удобного вывода.
     *
     * @param books Список объектов Book.
     * @return Отформатированный список строк.
     */
    public static List<String> formatBooks(List<Book> books) {
        List<String> formattedBooks = new ArrayList<>();
        for (Book book : books) {
            formattedBooks.add(String.format(
                    "ID: %d, Название: %s, Жанр: %s, Автор: %s %s",
                    book.getId(),
                    book.getTitle(),
                    book.getGenre(),
                    book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName()
            ));
        }
        return formattedBooks;
    }

    /**
     * Форматирует одну книгу для вывода.
     *
     * @param book Объект Book.
     * @return Отформатированная строка с данными о книге.
     */
    public static String formatBook(Book book) {
        return String.format(
                "ID: %d, Название: %s, Жанр: %s, Автор: %s %s",
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }
}
