package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(52);
        for (int index = 0; index < 52; index++) {
            this.cards.add(new Card(
                    Rank.getRankByValue(index % 13),
                    Suit.getSuitByValue(index % 4)));
        }
    }

    public Card drawCard() {
        if (this.cards.isEmpty()) return null;
        int random = ThreadLocalRandom.current().nextInt(0, this.cards.size() - 1);
        Card result = this.cards.get(random);
        this.cards.remove(result);
        return result;
    }
}
