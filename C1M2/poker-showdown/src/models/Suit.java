package models;

import java.util.Arrays;

public enum Suit {

    CLUB(1), DIAMOND(2), HEART(3), SPADE(4);

    private final int value;

    Suit(int value) {
        this.value = value;
    }

    /**
     * return > 1 if the suit of the other is larger than this suit.
     * return == 1 if the suit of the other is equal to this suit.
     * return < 1 if the suit of the other is smaller than this suit.
     */
    public int compare(Suit other) {
        return other.value - this.value;
    }

    public static Suit getSuitByValue(int value) {
        return Arrays.stream(Suit.values())
                .filter(suit -> suit.value == value)
                .findFirst()
                .orElse(CLUB);
    }
}
