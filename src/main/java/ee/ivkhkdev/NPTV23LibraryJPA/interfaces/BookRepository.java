package ee.ivkhkdev.NPTV23LibraryJPA.interfaces;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Удален метод existsByAuthor_Id, так как поле author больше не используется
}
