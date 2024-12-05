package ee.ivkhkdev.NPTV23LibraryJPA.interfaces;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);

    // Метод для проверки, есть ли книги, связанные с автором по ID
    boolean existsByAuthorId(Long authorId);
}