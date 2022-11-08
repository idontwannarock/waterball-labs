package models;

public abstract class Player {

    protected final String order;
    private int point;
    private Hand hand;
    protected String name;

    public Player(String order) {
        this.order = order;
        point = 0;
    }

    public abstract void nameHimOrHerself();

    public void drawCard(Deck deck) {
        if (this.hand == null) {
            this.hand = new Hand();
        }
        this.hand.addCard(deck.drawCard());
    }

    public String getOrder() {
        return order;
    }

    public int getPoint() {
        return point;
    }
}
