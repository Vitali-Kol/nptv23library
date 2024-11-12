package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;

import java.util.List;
import java.util.Scanner;

public class UserAppHelper implements AppHelper<User> {
    private final Input input;

    // Конструктор по умолчанию
    public UserAppHelper() {
        this.input = new Input() {
            private final Scanner scanner = new Scanner(System.in);

            @Override
            public String getString() {
                return scanner.nextLine();
            }
        };
    }

    // Конструктор для внедрения зависимостей (используется в тестах)
    public UserAppHelper(Input input) {
        this.input = input;
    }

    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя читателя: ");
            user.setFirstname(input.getString());
            System.out.print("Фамилия читателя: ");
            user.setLastname(input.getString());
            return user;
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> update(List<User> entities) {
        // Реализуйте метод обновления, используя input, если необходимо
        return List.of();
    }

    @Override
    public boolean printList(List<User> users) {
        System.out.println("---------- Список читателей --------");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s. %s%n", i + 1, user.getFirstname(), user.getLastname(), user.getPhone());
        }
        return false;
    }
}
