package org.example.collisiondetection.sprites;

/**
 * A doubly-linked-list that persists the head of the list.
 */
public class Sprites {

    /**
     * Sprite with the smallest coordinate
     */
    private Sprite head = null;

    public Sprite getSprites() {
        return this.head;
    }

    public void add(Sprite sprite) {
        if (isTheFirstNode()) {
            addFirstNode(sprite);
        } else if (isHead(sprite)) {
            insertHeadNode(sprite);
        } else if (isTail(sprite)) {
            appendTailNode(sprite);
        } else {
            insertMiddleNode(sprite);
        }
    }

    public Sprite findBy(int coordinate) {
        if (this.head != null) {
            if (this.head.getCoordinate() == coordinate) return this.head;
            Sprite current = this.head;
            while (current.hasNext()) {
                current = current.getNext();
                if (current.getCoordinate() == coordinate) {
                    return current;
                }
            }
        }
        throw new IllegalArgumentException("There is no Sprite on the coordinate " + coordinate + ".");
    }

    public void move(Sprite target, int coordinate) {
        remove(target);
        target.setCoordinate(coordinate);
        add(target);
    }

    public void remove(Sprite target) {
        if (this.head.getCoordinate() == target.getCoordinate()) {
            if (!this.head.hasNext()) {
                this.head = null;
                return;
            }
            this.head = this.head.getNext();
            return;
        }
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.getNext();
            if (current.getCoordinate() == target.getCoordinate()) {
                Sprite prev = current.getPrev();
                prev.setNext(null);
                current.setPrev(null);
                if (current.hasNext()) {
                    prev.setNext(current.getNext());
                    current.getNext().setPrev(prev);
                    current.setNext(null);
                }
                break;
            }
        }
    }

    public void print() {
        Sprite current = this.head;
        System.out.print(current.getName() + ":" + current.getCoordinate());
        while (current.hasNext()) {
            current = current.getNext();
            System.out.print(", ");
            System.out.print(current.getName() + ":" + current.getCoordinate());
        }
        System.out.println();
    }

    private boolean isTheFirstNode() {
        return head == null;
    }

    private void addFirstNode(Sprite sprite) {
        head = sprite;
    }

    private boolean isHead(Sprite sprite) {
        return this.head.getCoordinate() > sprite.getCoordinate();
    }

    private void insertHeadNode(Sprite sprite) {
        this.head.setPrev(sprite);
        sprite.setNext(this.head);
        this.head = sprite;
    }

    private boolean isTail(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            if (current.getCoordinate() > sprite.getCoordinate()) {
                return false;
            }
            current = current.getNext();
        }
        return current.getCoordinate() <= sprite.getCoordinate();
    }

    private void appendTailNode(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.getNext();
        }
        current.setNext(sprite);
        sprite.setPrev(current);
    }

    private void insertMiddleNode(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.getNext();
            if (sprite.getCoordinate() < current.getCoordinate()) {
                Sprite prev = current.getPrev();
                prev.setNext(sprite);
                current.setPrev(sprite);
                sprite.setPrev(prev);
                sprite.setNext(current);
                break;
            }
        }
    }
}
