package ee.ivkhkdev.NPTV23LibraryJPA.services;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title, String genre) {
        if (title == null || title.trim().isEmpty() || genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги и жанр обязательны.");
        }

        Book book = new Book(title.trim(), genre.trim());
        bookRepository.save(book);
    }

    // Метод для получения всех книг в читабельном формате
    public String getAllBooksFormatted() {
        StringBuilder formattedBooks = new StringBuilder();
        for (Book book : bookRepository.findAll()) {
            formattedBooks.append(String.format("ID: %d, Название: %s, Жанр: %s\n",
                    book.getId(),
                    book.getTitle(),
                    book.getGenre()));
        }
        return formattedBooks.toString();
    }

    // Метод для удаления книги по ID
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Книга с таким ID не найдена.");
        }
        bookRepository.deleteById(id);
    }
}
