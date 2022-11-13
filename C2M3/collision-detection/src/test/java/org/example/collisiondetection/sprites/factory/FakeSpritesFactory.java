package org.example.collisiondetection.sprites.factory;

import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Sprites;

public class FakeSpritesFactory implements SpritesFactory {

    private final Sprite[] sprites;

    public FakeSpritesFactory(Sprite... sprites) {
        this.sprites = sprites;
    }
    @Override
    public void create(Sprites sprites) {
        for (Sprite sprite : this.sprites) {
            sprites.add(sprite);
        }
    }
}
