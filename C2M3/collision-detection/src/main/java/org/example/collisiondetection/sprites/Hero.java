package org.example.collisiondetection.sprites;

public class Hero extends Sprite {

    private int hp = 30;

    public Hero(int coordinate) {
        super(coordinate, "H");
    }

    public int getHp() {
        return this.hp;
    }

    /**
     * Reduce hp by input.
     */
    public void reduceHpBy(int reduce) {
        hp -= reduce;
    }

    public void increaseHpBy(int increase) {
        hp += increase;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isNotAlive() {
        return !isAlive();
    }
}
