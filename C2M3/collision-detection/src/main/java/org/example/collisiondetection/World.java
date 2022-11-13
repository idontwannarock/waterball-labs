package org.example.collisiondetection;

import org.example.collisiondetection.collision.CollisionHandler;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;
import org.example.collisiondetection.sprites.factory.SpritesFactory;

public class World {

    protected final Sprites sprites = new Sprites();

    private final CollisionHandler collisionHandler;

    public World(SpritesFactory spritesFactory, CollisionHandler collisionHandler) {
        spritesFactory.create(sprites);
        this.collisionHandler = collisionHandler;
    }

    public void move(int beginning, int destination) {
        if (isNotValidCoordinates(beginning, destination)) throw new IllegalArgumentException("The coordinates in not valid.");

        Sprite sprite = getSpriteOn(beginning);

        if (reachedDestination(beginning, destination)) return;

        while (destinationNotReached(sprite, destination)) {
            if (isThereSpriteAhead(sprite, destination)) {
                boolean result = moveToTheNextSpriteCoordinate(sprite, destination);
                if (movementUnsuccessful(result)) {
                    break;
                }
            } else {
                move(sprite, destination);
                return;
            }
        }
    }

    public void printWorld() {
        this.sprites.print();
    }

    private boolean isNotValidCoordinates(int beginning, int destination) {
        return beginning < 0 || beginning > 29 || destination < 0 || destination > 29;
    }

    private Sprite getSpriteOn(int beginning) {
        return sprites.findBy(beginning);
    }

    private boolean reachedDestination(int current, int destination) {
        return current == destination;
    }

    private boolean destinationNotReached(Sprite current, int destination) {
        return current.coordinate != destination;
    }

    private boolean isThereSpriteAhead(Sprite current, int destination) {
        return current.isMovingForward(destination)
                ? current.hasNext() && current.next.coordinate <= destination
                : current.hasPrev() && current.prev.coordinate >= destination;
    }

    private boolean moveToTheNextSpriteCoordinate(Sprite target, int destination) {
        return collisionHandler.handle(sprites, target, target.isMovingForward(destination));
    }

    private static boolean movementUnsuccessful(boolean isMovingSuccessfully) {
        return !isMovingSuccessfully;
    }

    private void move(Sprite current, int destination) {
        sprites.move(current, destination);
    }
}
