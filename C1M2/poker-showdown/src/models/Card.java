package models;

public class Card {

    private final Rank rank;
    private final Suit suit;


    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isBiggerThan(Card card) {
        return this.rank.compare(card.rank) == 0
                ? this.suit.compare(card.suit) >= 0
                : this.rank.compare(card.rank) > 0;
    }
}
