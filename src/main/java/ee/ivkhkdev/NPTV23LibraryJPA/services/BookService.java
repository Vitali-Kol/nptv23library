package ee.ivkhkdev.NPTV23LibraryJPA.services;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.AuthorRepository;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void addBook(String title, String genre, String authorFirstName, String authorLastName) {
        if (title == null || title.trim().isEmpty() ||
                genre == null || genre.trim().isEmpty() ||
                authorFirstName == null || authorFirstName.trim().isEmpty() ||
                authorLastName == null || authorLastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Все поля должны быть заполнены.");
        }

        // Найдите автора по имени и фамилии
        Author author = authorRepository.findByFirstNameAndLastName(authorFirstName, authorLastName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Автор не найден"));

        // Создайте и сохраните книгу
        Book book = new Book(title.trim(), genre.trim(), author);
        bookRepository.save(book);
    }

    public void addBook(String title, String genre) {
    }
}
