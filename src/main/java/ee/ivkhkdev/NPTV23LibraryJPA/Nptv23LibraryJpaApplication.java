package ee.ivkhkdev.NPTV23LibraryJPA;

import ee.ivkhkdev.NPTV23LibraryJPA.entity.Author;
import ee.ivkhkdev.NPTV23LibraryJPA.entity.Book;
import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.Input;
import ee.ivkhkdev.NPTV23LibraryJPA.services.AuthorService;
import ee.ivkhkdev.NPTV23LibraryJPA.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Nptv23LibraryJpaApplication implements CommandLineRunner {

	@Autowired
	private Input input;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(Nptv23LibraryJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------ Библиотека группы NPTV23 с базой данных ------");
		boolean repeat = true;
		do {
			System.out.println("Список задач:");
			System.out.println("0. Выйти из программы");
			System.out.println("1. Добавить автора");
			System.out.println("2. Добавить книгу");
			System.out.println("3. Показать все книги");
			System.out.println("4. Удалить книгу по ID");
			System.out.println("5. Удалить автора по ID");
			System.out.println("6. Показать всех авторов");
			System.out.print("Введите номер задачи: ");
			int task = Integer.parseInt(input.getString());
			switch (task) {
				case 0:
					repeat = false;
					break;
				case 1:
					addAuthor();
					break;
				case 2:
					addBook();
					break;
				case 3:
					showBooks();
					break;
				case 4:
					deleteBook();
					break;
				case 5:
					deleteAuthor();
					break;
				case 6:
					showAuthors();
					break;
				default:
					System.out.println("Выберите задачу из списка!");
			}
			System.out.println("----------------------------------------");
		} while (repeat);
		System.out.println("До свидания :)");
	}

	private void addAuthor() {
		System.out.print("Введите имя автора: ");
		String firstName = input.getString();
		System.out.print("Введите фамилию автора: ");
		String lastName = input.getString();
		try {
			authorService.addAuthor(firstName, lastName);
			System.out.println("Автор успешно добавлен!");
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	private void addBook() {
		System.out.print("Введите название книги: ");
		String title = input.getString();
		System.out.print("Введите жанр книги: ");
		String genre = input.getString();
		System.out.print("Введите ID автора: ");
		Long authorId = Long.parseLong(input.getString());
		try {
			bookService.addBook(title, genre, authorId);
			System.out.println("Книга успешно добавлена!");
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	private void showBooks() {
		System.out.println("Список всех книг:");
		for (Book book : bookService.getAllBooks()) {
			System.out.println(book);
		}
	}

	private void deleteBook() {
		System.out.print("Введите ID книги для удаления: ");
		Long bookId = Long.parseLong(input.getString());
		try {
			bookService.deleteBookById(bookId);
			System.out.println("Книга успешно удалена!");
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	private void deleteAuthor() {
		System.out.print("Введите ID автора для удаления: ");
		Long authorId = Long.parseLong(input.getString());
		try {
			authorService.deleteAuthorById(authorId);
			System.out.println("Автор успешно удален!");
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	private void showAuthors() {
		System.out.println("Список всех авторов:");
		Iterable<Author> authors = authorService.getAllAuthors();
		for (Author author : authors) {
			System.out.println(author);
		}
	}
}
