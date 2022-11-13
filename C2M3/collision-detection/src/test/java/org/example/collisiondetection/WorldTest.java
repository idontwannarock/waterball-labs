package org.example.collisiondetection;

import org.example.collisiondetection.collision.*;
import org.example.collisiondetection.sprites.Fire;
import org.example.collisiondetection.sprites.Hero;
import org.example.collisiondetection.sprites.Sprite;
import org.example.collisiondetection.sprites.Water;
import org.example.collisiondetection.sprites.factory.FakeSpritesFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    private World world;

    @Test
    public void givenNewWorld_whenEnteringInvalidCoordinates_thenThrownIllegalArgumentException() {
        // arrange
        givenWorld(null);

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> this.world.move(-1, 30));
    }

    @Test
    public void givenTwoSpritesWorld_whenMoveFirstSpriteWithoutCollideWithTheOther_thenMoveSucceeded() {
        // arrange
        givenWorld(null, givenHeroAt(0), givenHeroAt(2));

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(1, this.world.sprites.head.coordinate);
    }

    @Test
    public void givenTwoFire_whenMoveFirstFireCollideWithTheOther_thenMoveFailed() {
        // arrange
        Fire first = givenFireAt(0);
        Fire second = givenFireAt(1);
        givenWorld(new FireFireCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(first, this.world.sprites.head);
        assertEquals(second, this.world.sprites.head.next);
    }

    @Test
    public void givenHeroAndFire_whenMoveHeroCollideWithFire_thenMoveSucceededAndHeroLostTenHpAndFireRemoved() {
        // arrange
        Hero first = givenHeroAt(0);
        Fire second = givenFireAt(1);
        givenWorld(new HeroFireCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(first, this.world.sprites.head);
        assertEquals(20, ((Hero) this.world.sprites.head).hp);
        assertNull(this.world.sprites.head.next);
    }

    @Test
    public void givenFireHero_whenMoveFireCollideWithHero_thenFireRemovedAndHeroLostTenHp() {
        // arrange
        Fire first = givenFireAt(0);
        Hero second = givenHeroAt(1);
        givenWorld(new HeroFireCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(second, this.world.sprites.head);
        assertEquals(20, ((Hero) this.world.sprites.head).hp);
        assertNull(this.world.sprites.head.next);
    }

    @Test
    public void givenTwoHero_whenMoveHeroCollideWithTheOther_thenMoveFailed() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero second = givenHeroAt(1);
        givenWorld(new HeroHeroCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(first, this.world.sprites.head);
        assertEquals(second, this.world.sprites.head.next);
    }

    @Test
    public void givenHeroAndWater_whenMoveHeroCollideWithWater_thenMoveSucceededAndHeroGainedTenHpAndWaterRemoved() {
        // arrange
        Hero first = givenHeroAt(0);
        Water second = givenWaterAt(1);
        givenWorld(new HeroWaterCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(first, this.world.sprites.head);
        assertEquals(40, ((Hero) this.world.sprites.head).hp);
        assertNull(this.world.sprites.head.next);
    }

    @Test
    public void givenWaterAndHero_whenMoveWaterCollideWithHero_thenWaterRemovedAndHeroGainedTenHp() {
        // arrange
        Water first = givenWaterAt(0);
        Hero second = givenHeroAt(1);
        givenWorld(new HeroWaterCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(second, this.world.sprites.head);
        assertEquals(40, ((Hero) this.world.sprites.head).hp);
        assertNull(this.world.sprites.head.next);
    }

    @Test
    public void givenWaterAndFire_whenMoveWaterCollideWithFire_thenBothWaterAndFireRemoved() {
        // arrange
        Water first = givenWaterAt(0);
        Fire second = givenFireAt(1);
        givenWorld(new WaterFireCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertNull(this.world.sprites.head);
    }

    @Test
    public void givenTwoWater_whenMoveFirstWaterCollideWithTheOther_thenMoveFailed() {
        // arrange
        Water first = givenWaterAt(0);
        Water second = givenWaterAt(1);
        givenWorld(new WaterWaterCollisionHandler(null), first, second);

        // act
        moveFirstSpriteOneStep();

        // assert
        assertEquals(first, this.world.sprites.head);
        assertEquals(second, this.world.sprites.head.next);
    }

    private void givenWorld(CollisionHandler collisionHandler, Sprite... sprites) {
        this.world = new World(givenFakeFactory(sprites), collisionHandler);
    }

    private FakeSpritesFactory givenFakeFactory(Sprite... sprites) {
        return new FakeSpritesFactory(sprites);
    }

    private Hero givenHeroAt(int coordinate) {
        return new Hero(coordinate);
    }

    private Fire givenFireAt(int coordinate) {
        return new Fire(coordinate);
    }

    private Water givenWaterAt(int coordinate) {
        return new Water(coordinate);
    }

    private void moveFirstSpriteOneStep() {
        this.world.move(0, 1);
    }
}
