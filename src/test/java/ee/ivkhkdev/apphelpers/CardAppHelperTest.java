package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardAppHelperTest {
    private Service<Book> bookService;
    private Service<User> userService;
    private CardAppHelper cardAppHelper;

    @BeforeEach
    void setUp() {
        bookService = mock(Service.class);
        userService = mock(Service.class);
        cardAppHelper = new CardAppHelper(bookService, userService);
    }
    @Test
    void testCreateCardSuccessfully() {
        // Подготовка данных
        Author author = new Author("Lev","Tolstoy");
        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);
        User user = new User();
        List<User> users = List.of(user);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone("123456");
        List<Book> books = List.of(book);
        // Мокируем поведение сервисов
        when(bookService.list()).thenReturn(books);
        when(userService.list()).thenReturn(users);

        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        // Мокаем ввод пользователя через spy
        doReturn("1","n","1").when(spyCardAppHelper).getString(); // Симулируем выбор книги

        // Создание карты
        Card card = spyCardAppHelper.create();

        // Проверка, что карта была успешно создана
        assertNotNull(card);
        assertEquals("Voina i mir", card.getBook().getTitle());
        assertEquals("John", card.getUser().getFirstname());
        assertEquals("Doe", card.getUser().getLastname());
        assertEquals(LocalDate.now(), card.getBorrowedBookDate());
    }
    @Test
    void testCreateCardWithInvalidInput() {
        // Мокируем неправильный ввод
        when(bookService.list()).thenReturn(List.of(new Book()));
        when(userService.list()).thenReturn(List.of(new User()));

        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        // Мокаем некорректный ввод через spy
        doReturn("invalid").when(spyCardAppHelper).getString(); // Неверный ввод

        // Проверка, что карта не была создана
        Card card = spyCardAppHelper.create();
        assertNull(card);
    }
    @Test
    void testPrintListWithCards() {
        // Подготовка данных
        Author author = new Author("Lev","Tolstoy");
        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone("123456");
        Card card1 = new Card();
        card1.setBook(book);
        card1.setUser(user);
        card1.setReturnedBookDate(null);
        List<Card> cards = List.of(card1);
        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);
        // Мокаем вывод в консоль
        boolean result = spyCardAppHelper.printList(cards);
        assertTrue(result); // Ожидаем, что список выведен успешно
    }
    @Test
    void testPrintListNoCards() {
        // Пустой список карт
        List<Card> cards = List.of();

        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        // Мокаем вывод в консоль
        boolean result = spyCardAppHelper.printList(cards);

        assertFalse(result); // Ожидаем, что не было выведено никаких карт
    }
    @Test
    void testReturnBookSuccessfully() {
        // Подготовка данных
        Author author = new Author("Lev","Tolstoy");

        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone("123456");

        Card card1 = new Card();
        card1.setBook(book);
        card1.setUser(user);
        card1.setReturnedBookDate(null);

        List<Card> cards = List.of(card1);

        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        // Мокаем выбор книги для возврата
        doReturn("1").when(spyCardAppHelper).getString(); // Выбор карты для возврата

        // Выполняем возврат книги
        List<Card> updatedCards = spyCardAppHelper.returnBook(cards);

        // Проверяем, что дата возврата была установлена
        assertNotNull(updatedCards);
        assertEquals(LocalDate.now(), updatedCards.get(0).getReturnedBookDate());
    }
    @Test
    void testReturnBookWithInvalidCardSelection() {
        // Подготовка данных
        Author author = new Author("Lev","Tolstoy");

        Book book = new Book();
        book.setTitle("Voina i mir");
        book.getAuthors().add(author);
        book.setPublishedYear(2000);

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone("123456");

        Card card1 = new Card();
        card1.setBook(book);
        card1.setUser(user);
        card1.setReturnedBookDate(null);

        List<Card> cards = List.of(card1);

        // Создаем шпион для cardAppHelper
        CardAppHelper spyCardAppHelper = Mockito.spy(cardAppHelper);

        // Мокаем неверный ввод для выбора карты
        doReturn("invalid").when(spyCardAppHelper).getString(); // Неверный номер карты

        // Проверка, что возврат не был выполнен
        List<Card> updatedCards = spyCardAppHelper.returnBook(cards);
        assertNull(updatedCards); // Возврат должен быть неуспешным
    }
}