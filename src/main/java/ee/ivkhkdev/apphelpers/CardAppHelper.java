package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import java.time.LocalDate;
import java.util.List;

public class CardAppHelper implements AppHelper<Card>, Input {
    private final Service<Book> bookService;
    private final Service<User> userService;

    public CardAppHelper(Service<Book> bookService, Service<User>userService) {
        this.bookService = bookService;
        this.userService = userService;

    }
    @Override
    public Card create() {
        try {
            Card card = new Card();
            bookService.print();
            System.out.print("Выберите номер книги: ");
            int numberBook = Integer.parseInt(getString());
            Book book = bookService.list().get(numberBook-1);
            card.setBook(book);
            userService.print();
            System.out.print("Добавить нового автора (y/n): ");
            if("y".equals(getString())){ return null;}
            System.out.print("Выберите номер автора: ");
            int numberUser = Integer.parseInt(getString());
            User user = userService.list().get(numberUser-1);
            card.setUser(user);
            card.setBorrowedBookDate(LocalDate.now());
            return card;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean printList(List<Card> cards) {
        int counter = 0;
        System.out.println("--------- Список выданных книг --------");
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            if(card.getReturnedBookDate() != null){continue;}
            System.out.printf("%d. %s. читает: %s %s%d",
                    i+1,
                    card.getBook().getTitle(),
                    card.getUser().getFirstname(),
                    card.getUser().getLastname()
            );
            counter++;
        }
        System.out.println("--------- Конец списка --------");
        if(counter == 0){
            return false;
        }else{
            return true;
        }
    }

    public List<Card> returnBook(List<Card> cards) {
        return null;
    }
}
