package ee.ivkhkdev.NPTV23LibraryJPA.helpers;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorHelper {

    /**
     * Преобразует список авторов в строковое представление.
     * @param authors список объектов Author.
     * @return список строк с информацией об авторах.
     */
    public static List<String> formatAuthors(List<Author> authors) {
        return authors.stream()
                .map(author -> String.format("ID: %d, Имя: %s, Фамилия: %s",
                        author.getId(), author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, является ли объект Author валидным (имеет имя и фамилию).
     * @param author объект Author.
     * @return true, если объект валиден; false в противном случае.
     */
    public static boolean isValidAuthor(Author author) {
        return author != null &&
                author.getFirstName() != null && !author.getFirstName().trim().isEmpty() &&
                author.getLastName() != null && !author.getLastName().trim().isEmpty();
    }

    /**
     * Создает объект Author на основе имени и фамилии.
     * @param firstName имя автора.
     * @param lastName фамилия автора.
     * @return объект Author.
     */
    public static Author createAuthor(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя и фамилия автора должны быть заполнены.");
        }
        return new Author(firstName, lastName);
    }
}
