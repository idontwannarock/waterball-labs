package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Fire;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;
import org.example.collisiondetection.sprites.Water;

public class WaterFireCollisionHandler extends CollisionHandler {

    public WaterFireCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return (current instanceof Water && target instanceof Fire)
                || (current instanceof Fire && target instanceof Water);
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        sprites.remove(current);
        sprites.remove(target);
        return false;
    }
}
