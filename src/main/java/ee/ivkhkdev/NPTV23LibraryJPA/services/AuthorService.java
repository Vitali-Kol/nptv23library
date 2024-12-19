package ee.ivkhkdev.NPTV23LibraryJPA.services;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Автор с таким ID не найден.");
        }
    }

    // Метод для получения всех авторов в читабельном формате
    public String getAllAuthorsFormatted() {
        StringBuilder formattedAuthors = new StringBuilder();
        for (Author author : authorRepository.findAll()) {
            formattedAuthors.append(String.format("ID: %d, Имя: %s, Фамилия: %s\n",
                    author.getId(),
                    author.getFirstName(),
                    author.getLastName()));
        }
        return formattedAuthors.toString();
    }

    // Метод для получения всех авторов
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}