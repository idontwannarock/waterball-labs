package models;

import java.util.Arrays;

public enum Rank {

    TWO(0), THREE(1), FOUR(2), FIVE(3), SIX(4), SEVEN(5), EIGHT(6), NINE(7), TEN(8), JACK(9), QUEEN(10), KING(11), ACE(12);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    /**
     * return > 1 if the rank of the other is larger than this rank.
     * return == 1 if the rank of the other is equal to this rank.
     * return < 1 if the rank of the other is smaller than this rank.
     */
    public int compare(Rank other) {
        return other.value - this.value;
    }

    public static Rank getRankByValue(int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElse(TWO);
    }
}
