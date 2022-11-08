package exceptions;

public class PlayersSizeOverflowException extends RuntimeException {

    public PlayersSizeOverflowException(int playerSize) {
        System.out.println(playerSize + " players exceeds the 4 players restriction.");
    }
}
