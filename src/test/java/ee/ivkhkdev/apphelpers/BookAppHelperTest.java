package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookAppHelperTest {
    private BookAppHelper bookAppHelper;
    private Service<Author> authorService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        authorService = mock(Service.class);
        bookAppHelper = new BookAppHelper(authorService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreateWithExistingAuthors() {
        // Мокируем список авторов
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Имя1", "Фамилия1"));
        authors.add(new Author("Имя2", "Фамилия2"));
        when(authorService.list()).thenReturn(authors);
        doNothing().when(authorService).print();

        // Мокируем ввод
        BookAppHelper spyHelper = Mockito.spy(bookAppHelper);
        // Не добавляем нового автора(n).Указываем количество авторов(1). Выбор автора (1), Год издания(2024)
        doReturn("НазваниеКниги","n","1","1","2024").when(spyHelper).getString();

        Book book = spyHelper.create();

        assertNotNull(book);
        assertEquals("НазваниеКниги", book.getTitle());
        assertEquals(1, book.getAuthors().size());
        assertEquals("Имя1", book.getAuthors().get(0).getAuthorName());
        assertEquals("Фамилия1", book.getAuthors().get(0).getAuthorSurname());
        assertEquals(2024, book.getPublishedYear());
    }

    @Test
    public void testPrintList() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Книга1");
        book1.setPublishedYear(2021);
        book1.getAuthors().add(new Author("Имя1", "Фамилия1"));
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("Книга2");
        book2.setPublishedYear(2022);
        book2.getAuthors().add(new Author("Имя2", "Фамилия2"));
        books.add(book2);

        bookAppHelper.printList(books);

        String expectedOutput1 = "1. Книга1. Имя1 Фамилия1. 2021";
        String expectedOutput2 = "2. Книга2. Имя2 Фамилия2. 2022";


        assertTrue(outContent.toString().contains(expectedOutput1));
        assertTrue(outContent.toString().contains(expectedOutput2));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}