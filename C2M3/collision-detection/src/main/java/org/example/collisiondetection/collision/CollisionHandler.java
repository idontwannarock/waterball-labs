package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;

public abstract class CollisionHandler {

    private final CollisionHandler nextHandler;

    protected CollisionHandler(CollisionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Handle collision between Sprites assuming there is another Sprite on the moving direction.
     * @return true if target Sprite moved to the next sprite's coordinate; false if the Sprite failed to move or target Sprite removed.
     */
    public boolean handle(Sprites sprites, Sprite target, boolean isMovingForward) {
        if (isMatched(target, isMovingForward)) {
            return doHandling(sprites, target, isMovingForward);
        } else if (hasNextHandler()) {
            return doNextHandling(sprites, target, isMovingForward);
        } else {
            return false;
        }
    }

    protected abstract boolean isMatched(Sprite current, Sprite target);

    protected abstract boolean doHandling(Sprites sprites, Sprite current, Sprite target);

    private boolean isMatched(Sprite current, boolean isMovingForward) {
        Sprite target = getTarget(current, isMovingForward);
        return isMatched(current, target);
    }

    private boolean doHandling(Sprites sprites, Sprite current, boolean isMovingForward) {
        Sprite target = getTarget(current, isMovingForward);
        return doHandling(sprites, current, target);
    }

    private Sprite getTarget(Sprite current, boolean isMovingForward) {
        return isMovingForward ? current.getNext() : current.getPrev();
    }

    private boolean hasNextHandler() {
        return nextHandler != null;
    }

    private boolean doNextHandling(Sprites sprites, Sprite current, boolean isForward) {
        return nextHandler.handle(sprites, current, isForward);
    }
}
