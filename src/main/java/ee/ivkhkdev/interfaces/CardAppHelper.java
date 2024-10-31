package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Card;

import java.util.List;

public class CardAppHelper implements AppHelper<Card>{
    @Override
    public Card create() {
        return null;
    }

    @Override
    public boolean printList(List<Card> elements) {

        return false;
    }
}
