package org.example.collisiondetection.sprites;

public abstract class Sprite {

    private final String name;

    private int coordinate;

    private Sprite prev;

    private Sprite next;

    protected Sprite(int coordinate, String name) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public int getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public Sprite getPrev() {
        return this.prev;
    }

    public void setPrev(Sprite prev) {
        this.prev = prev;
    }

    public Sprite getNext() {
        return this.next;
    }

    public void setNext(Sprite next) {
        this.next = next;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMovingForward(int destination) {
        return this.coordinate < destination;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public boolean hasPrev() {
        return this.prev != null;
    }

    @Override
    public String toString() {
        return name + ":" + coordinate;
    }
}
