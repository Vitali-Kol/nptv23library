package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Card;

import java.util.List;

public interface AppHelper<T> {
    T create();
    boolean printList(List<T> elements);

}
