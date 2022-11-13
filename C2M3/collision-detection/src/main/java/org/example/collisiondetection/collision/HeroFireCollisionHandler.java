package org.example.collisiondetection.collision;

import org.example.collisiondetection.sprites.Fire;
import org.example.collisiondetection.sprites.Hero;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;

public class HeroFireCollisionHandler extends CollisionHandler {

    public HeroFireCollisionHandler(CollisionHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean isMatched(Sprite current, Sprite target) {
        return (current instanceof Hero && target instanceof Fire)
                || (current instanceof Fire && target instanceof Hero);
    }

    @Override
    protected boolean doHandling(Sprites sprites, Sprite current, Sprite target) {
        if (current instanceof Hero) {
            Hero hero = (Hero) current;
            hero.reduceHpBy(10);
            sprites.remove(target);
            return hero.isAlive();
        } else {
            Hero hero = (Hero) target;
            hero.reduceHpBy(10);
            sprites.remove(current);
            if (hero.isNotAlive()) {
                sprites.remove(hero);
            }
            return false;
        }
    }
}
