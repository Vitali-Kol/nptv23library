package ee.ivkhkdev.NPTV23LibraryJPA;

import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.Input;
import ee.ivkhkdev.NPTV23LibraryJPA.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Nptv23LibraryJpaApplication implements CommandLineRunner {

	@Autowired
	private Input input;

	@Autowired
	private AuthorService authorService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------ Библиотека группы NPTV23 с базой данных ------");
		boolean repeat = true;
		do {
			System.out.println("Список задач:");
			System.out.println("0. Выйти из программы");
			System.out.println("1. Добавить автора");
			System.out.print("Введите номер задачи: ");
			int task = Integer.parseInt(input.getString());
			switch (task) {
				case 0:
					repeat = false;
					break;
				case 1:
					addAuthor();
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

	public static void main(String[] args) {
		SpringApplication.run(Nptv23LibraryJpaApplication.class, args);
	}
}
