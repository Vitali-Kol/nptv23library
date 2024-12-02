package ee.ivkhkdev.NPTV23LibraryJPA.interfaces;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Найти авторов по имени.
     *
     * @param firstName имя автора.
     * @return список авторов с указанным именем.
     */
    List<Author> findByFirstName(String firstName);

    /**
     * Найти авторов по фамилии.
     *
     * @param lastName фамилия автора.
     * @return список авторов с указанной фамилией.
     */
    List<Author> findByLastName(String lastName);

    /**
     * Найти авторов по имени и фамилии.
     *
     * @param firstName имя автора.
     * @param lastName фамилия автора.
     * @return список авторов с указанными именем и фамилией.
     */
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
