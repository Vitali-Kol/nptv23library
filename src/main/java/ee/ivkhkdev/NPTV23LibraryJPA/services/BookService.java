package ee.ivkhkdev.NPTV23LibraryJPA.services;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.helpers.AuthorHelper;
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

    // Метод для добавления книги с автором
    public void addBook(String title, String genre, String authorFirstName, String authorLastName) {
        if (title == null || title.trim().isEmpty() ||
                genre == null || genre.trim().isEmpty() ||
                authorFirstName == null || authorFirstName.trim().isEmpty() ||
                authorLastName == null || authorLastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Все поля должны быть заполнены.");
        }

        // Используем AuthorHelper для создания или поиска автора
        Author author = AuthorHelper.createAuthor(authorFirstName, authorLastName);
        Author existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName())
                .stream()
                .findFirst()
                .orElseGet(() -> authorRepository.save(author)); // Если автор не найден, сохраняем его

        // Создаем и сохраняем книгу с найденным или новым автором
        Book book = new Book(title.trim(), genre.trim(), existingAuthor);
        bookRepository.save(book);
    }

    // Метод для добавления книги без указания автора
    public void addBook(String title, String genre) {
        if (title == null || title.trim().isEmpty() || genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги и жанр обязательны.");
        }

        // Создаем книгу без автора
        Book book = new Book(title.trim(), genre.trim(), null); // Без автора
        bookRepository.save(book);
    }

    // Метод для получения всех книг
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Метод для удаления книги по ID
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Книга с таким ID не найдена.");
        }
        bookRepository.deleteById(id);
    }
}
