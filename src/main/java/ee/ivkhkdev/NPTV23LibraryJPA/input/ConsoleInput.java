package ee.ivkhkdev.NPTV23LibraryJPA.input;

import ee.ivkhkdev.NPTV23LibraryJPA.interfaces.Input;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Override
    public String getString() {
        return scanner.nextLine();
    }
}
