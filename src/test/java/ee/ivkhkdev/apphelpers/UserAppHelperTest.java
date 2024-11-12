package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAppHelperTest {

    private UserAppHelper userAppHelper;

    @Mock
    private Input inputMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userAppHelper = new UserAppHelper(inputMock); // Внедряем мок Input
    }

    @Test
    void testCreate_UserCreationSuccess() {
        // Настраиваем поведение мока для ввода имени и фамилии
        when(inputMock.getString()).thenReturn("Иван", "Иванов");

        // Создаем пользователя
        User user = userAppHelper.create();

        // Проверяем, что созданный пользователь имеет ожидаемые значения
        assertNotNull(user);
        assertEquals("Иван", user.getFirstname());
        assertEquals("Иванов", user.getLastname());

        // Проверяем количество вызовов getString
        verify(inputMock, times(2)).getString();
    }

    @Test
    void testCreate_UserCreationFailure() {
        // Настраиваем поведение мока для выбрасывания исключения
        when(inputMock.getString()).thenThrow(new RuntimeException("Ошибка ввода"));

        // Создаем пользователя
        User user = userAppHelper.create();

        // Проверяем, что метод вернул null при ошибке
        assertNull(user);

        // Проверяем, что getString был вызван хотя бы один раз
        verify(inputMock, atLeastOnce()).getString();
    }

    @Test
    void testPrintList() {
        // Создаем несколько пользователей
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstname("Иван");
        user1.setLastname("Иванов");
        user1.setPhone("123456789");
        users.add(user1);

        User user2 = new User();
        user2.setFirstname("Петр");
        user2.setLastname("Петров");
        user2.setPhone("987654321");
        users.add(user2);

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Вызов метода
        userAppHelper.printList(users);

        // Восстанавливаем вывод в консоль
        System.setOut(originalSystemOut);

        // Проверяем, что вывод на консоль соответствует ожиданиям
        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. Иван Иванов. 123456789"));
        assertTrue(output.contains("2. Петр Петров. 987654321"));
    }
}
