package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;
import org.example.collisiondetection.sprites.Water;

public class WaterWaterCollisionHandler extends CollisionHandler {

    public WaterWaterCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return current instanceof Water && target instanceof Water;
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        return false;
    }
}
