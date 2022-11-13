package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Hero;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;

public class HeroHeroCollisionHandler extends CollisionHandler {

    public HeroHeroCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return current instanceof Hero && target instanceof Hero;
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        return false;
    }
}
