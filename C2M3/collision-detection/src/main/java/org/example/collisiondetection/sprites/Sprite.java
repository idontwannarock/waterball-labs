package org.example.collisiondetection.sprites;

public abstract class Sprite {
    public int coordinate;

    public Sprite prev;

    public Sprite next;
    private final String name;

    public Sprite(int coordinate, String name) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public boolean isMovingForward(int destination) {
        return this.coordinate < destination;
    }

    public String getName() {
        return this.name;
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
