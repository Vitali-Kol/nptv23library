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

    public void addAuthor(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и фамилия автора должны быть заполнены.");
        }
        Author author = new Author(firstName.trim(), lastName.trim());
        authorRepository.save(author);
    }
}
