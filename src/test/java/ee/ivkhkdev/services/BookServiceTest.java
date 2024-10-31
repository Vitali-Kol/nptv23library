package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private AppHelper<Book> appHelperBook;

    @Mock
    private FileRepository<Book> bookRepository;

    @InjectMocks
    private BookService bookService;

    private List<Book> books;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        books = new ArrayList<>();
        bookService = new BookService(books, appHelperBook);
        when(appHelperBook.getRepository()).thenReturn(bookRepository);
    }

    @Test
    void testAdd() {
        Book book = new Book();
        when(appHelperBook.create()).thenReturn(book);

        boolean result = bookService.add();

        assertTrue(result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testAddFailsWhenAppHelperReturnsNull() {
        when(appHelperBook.create()).thenReturn(null);

        boolean result = bookService.add();

        assertFalse(result);
        verify(bookRepository, never()).save((Book) any());
    }

    @Test
    void testEdit() {
        Book existingBook = new Book();
        books.add(existingBook);

        boolean result = bookService.edit(existingBook);

        assertFalse(result); // Метод edit еще не реализован, ожидаем false
    }

    @Test
    void testRemove() {
        Book book = new Book();
        books.add(book);

        boolean result = bookService.remove(book);

        assertFalse(result); // Метод remove еще не реализован, ожидаем false
    }

    @Test
    void testPrint() {
        bookService.print();

        verify(appHelperBook, times(1)).printList(books);
    }

    @Test
    void testList() {
        List<Book> loadedBooks = new ArrayList<>();
        when(bookRepository.load()).thenReturn(loadedBooks);

        List<Book> result = bookService.list();

        assertEquals(loadedBooks, result);
        verify(bookRepository, times(1)).load();
    }
}
