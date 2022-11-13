package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Hero;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;
import org.example.collisiondetection.sprites.Water;

public class HeroWaterCollisionHandler extends CollisionHandler {

    public HeroWaterCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return (current instanceof Hero && target instanceof Water)
                || (current instanceof Water && target instanceof Hero);
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        if (current instanceof Hero) {
            Hero hero = (Hero) current;
            hero.increaseHpBy(10);
            sprites.remove(target);
            return true;
        } else {
            Hero hero = (Hero) target;
            ((Hero) target).increaseHpBy(10);
            sprites.remove(current);
            return false;
        }
    }
}
