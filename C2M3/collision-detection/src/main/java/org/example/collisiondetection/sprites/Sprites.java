package org.example.collisiondetection.sprites;

/**
 * A doubly-linked-list that persists the head of the list.
 */
public class Sprites {

    /**
     * Sprite with the smallest coordinate
     */
    public Sprite head = null;

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
            if (this.head.coordinate == coordinate) return this.head;
            Sprite current = this.head;
            while (current.hasNext()) {
                current = current.next;
                if (current.coordinate == coordinate) {
                    return current;
                }
            }
        }
        throw new IllegalArgumentException("There is no Sprite on the coordinate " + coordinate + ".");
    }

    public void move(Sprite target, int coordinate) {
        remove(target);
        target.coordinate = coordinate;
        add(target);
    }

    public void remove(Sprite target) {
        if (this.head.coordinate == target.coordinate) {
            if (!this.head.hasNext()) {
                this.head = null;
                return;
            }
            this.head = this.head.next;
            return;
        }
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.next;
            if (current.coordinate == target.coordinate) {
                Sprite prev = current.prev;
                prev.next = null;
                current.prev = null;
                if (current.hasNext()) {
                    prev.next = current.next;
                    current.next.prev = prev;
                    current.next = null;
                }
                break;
            }
        }
    }

    public void print() {
        Sprite current = this.head;
        System.out.print(current.getName() + ":" + current.coordinate);
        while (current.hasNext()) {
            current = current.next;
            System.out.print(", ");
            System.out.print(current.getName() + ":" + current.coordinate);
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
        return this.head.coordinate > sprite.coordinate;
    }

    private void insertHeadNode(Sprite sprite) {
        this.head.prev = sprite;
        sprite.next = this.head;
        this.head = sprite;
    }

    private boolean isTail(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            if (current.coordinate > sprite.coordinate) {
                return false;
            }
            current = current.next;
        }
        return current.coordinate <= sprite.coordinate;
    }

    private void appendTailNode(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.next;
        }
        current.next = sprite;
        sprite.prev = current;
    }

    private void insertMiddleNode(Sprite sprite) {
        Sprite current = this.head;
        while (current.hasNext()) {
            current = current.next;
            if (sprite.coordinate < current.coordinate) {
                Sprite prev = current.prev;
                prev.next = sprite;
                current.prev = sprite;
                sprite.prev = prev;
                sprite.next = current;
                break;
            }
        }
    }
}
