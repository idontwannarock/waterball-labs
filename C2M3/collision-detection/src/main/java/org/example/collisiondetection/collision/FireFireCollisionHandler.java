package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Fire;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;

public class FireFireCollisionHandler extends CollisionHandler {

    public FireFireCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return current instanceof Fire && target instanceof Fire;
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        return false;
    }
}
