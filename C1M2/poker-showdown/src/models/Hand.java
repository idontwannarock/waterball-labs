package models;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>(13);
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }
}
