package models;

public class AiPlayer extends Player {

    public AiPlayer(String order) {
        super(order);
    }

    @Override
    public void nameHimOrHerself() {
        this.name = "AI Player " + order;
    }
}
