package ee.ivkhkdev.NPTV23LibraryJPA.services;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.AuthorRepository;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // Метод для добавления автора
    public void addAuthor(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и фамилия автора должны быть заполнены.");
        }
        Author author = new Author(firstName.trim(), lastName.trim());
        authorRepository.save(author);
    }

    // Метод для удаления автора по ID
    public void deleteAuthorById(Long id) {
        // Проверяем, есть ли книги, связанные с этим автором
        if (bookRepository.existsByAuthorId(id)) {
            throw new IllegalArgumentException("Невозможно удалить автора, так как он связан с книгами.");
        }
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Автор с таким ID не найден.");
        }
    }

    // Метод для получения всех авторов
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
