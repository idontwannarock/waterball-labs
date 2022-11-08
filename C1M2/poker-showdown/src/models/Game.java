package models;

import exceptions.PlayersSizeOverflowException;

public class Game {

    private final Player[] players;
    private final Deck deck;

    public Game(Player... players) {
        if (players.length > 4) {
            throw new PlayersSizeOverflowException(players.length);
        }
        this.players = players;
        this.deck = new Deck();
    }

    public void start() {

    }
}
