package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.repository.Storage;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.interfaces.Service;

import java.util.ArrayList;
import java.util.List;

public class NPTV23Library {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<User> users = new ArrayList<>(); // Инициализация списка пользователей

        // Создаем репозитории для хранения данных
        FileRepository<Book> bookRepository = new Storage<>("books.dat");
        FileRepository<Author> authorRepository = new Storage<>("authors.dat");
        FileRepository<User> userRepository = new Storage<>("users.dat");

        // Создаем AppHelper для авторов, книг и пользователей с учетом входных данных
        AppHelperAuthor appHelperAuthor = new AppHelperAuthor(authorRepository, System.in);
        Service<Author> authorService = new AuthorService(authors, appHelperAuthor);

        AppHelperBook appHelperBook = new AppHelperBook(appHelperAuthor, authorService, bookRepository);
        Service<Book> bookService = new BookService(books, appHelperBook);

        // Создаем AppHelperUser и UserService
        AppHelperUser appHelperUser = new AppHelperUser(userRepository, System.in);
        Service<User> userService = new UserService(users, appHelperUser); // Передаем appHelperUser и список users

        // Запускаем приложение
        App app = new App(bookService, authorService, userService);
        app.run();
    }
}
