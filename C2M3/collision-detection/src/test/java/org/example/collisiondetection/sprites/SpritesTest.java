package org.example.collisiondetection.sprites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpritesTest {

    private Sprites sprites;

    @BeforeEach
    public void setUp() {
        this.sprites = new Sprites();
    }

    @Test
    void givenEmptySprites_whenAddingOneSprite_thenPlaceGivenSpriteAtHead() {
        // arrange
        Hero sprite = givenHeroAt(0);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.getCoordinate(), this.sprites.getSprites().getCoordinate());
    }

    @Test
    void givenOneSpriteInSprites_whenAddingCloserSprite_thenInsertGivenSpriteAtHead() {
        // arrange
        givenSpritesWith(givenHeroAt(1));
        Hero sprite = givenHeroAt(0);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.getCoordinate(), this.sprites.getSprites().getCoordinate());
    }

    @Test
    void givenOneSpriteInSprites_whenAddingFurtherSprite_thenAppendGivenSpriteAtTail() {
        // arrange
        givenSpritesWith(givenHeroAt(0));
        Hero sprite = givenHeroAt(1);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.getCoordinate(), this.sprites.getSprites().getNext().getCoordinate());
    }

    @Test
    void givenTwoSpriteInSprites_whenAddingMiddleSprite_thenInsertGivenSpriteInTheMiddle() {
        // arrange
        givenSpritesWith(givenHeroAt(0), givenHeroAt(2));
        Hero sprite = givenHeroAt(1);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.getCoordinate(), this.sprites.getSprites().getNext().getCoordinate());
    }

    @Test
    void givenEmptySprites_whenFindSpriteByCoordinate_thenThrowIllegalArgumentException() {
        // act and assert
        assertThrows(IllegalArgumentException.class, () -> findSpriteByCoordinate(0));
    }

    @Test
    void givenOneSpriteInSprites_whenFindSpriteOnEmptySpot_thenThrowIllegalArgumentException() {
        // arrange
        givenSpritesWith(givenHeroAt(0));

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> findSpriteByCoordinate(1));
    }

    @Test
    void givenOneSpriteInSprites_whenFindSpriteOnNonEmptySpot_thenReturnGivenSprite() {
        // arrange
        givenSpritesWith(givenHeroAt(0));

        // act
        Sprite actual = findSpriteByCoordinate(0);

        // assert
        assertEquals(actual.getCoordinate(), this.sprites.getSprites().getCoordinate());
    }

    @Test
    void givenOneSpriteInSprites_whenMoveSpriteToAnotherSpot_thenMoveSucceeded() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite);

        // act
        moveSpriteTo(sprite, 1);

        // assert
        assertEquals(1, this.sprites.getSprites().getCoordinate());
    }

    @Test
    void givenTwoSpritesInSprites_whenMoveSecondSpriteToFurtherPlace_thenMoveSucceededWithOrderStaysTheSame() {
        // arrange
        Hero sprite = givenHeroAt(1);
        givenSpritesWith(givenHeroAt(0), sprite);

        // act
        moveSpriteTo(sprite, 2);

        // assert
        assertEquals(2, this.sprites.getSprites().getNext().getCoordinate());
    }

    @Test
    void givenTwoSpritesInSprites_whenMoveFirstSpriteToFurtherThenSecond_thenMoveSucceededWithOrderInverted() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite, givenHeroAt(1));

        // act
        moveSpriteTo(sprite, 2);

        // assert
        assertEquals(sprite, this.sprites.getSprites().getNext());
        assertEquals(2, this.sprites.getSprites().getNext().getCoordinate());
    }

    @Test
    void givenOneSpriteInSprites_whenRemoveOneExistingSprite_thenRemoveSucceeded() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite);

        // act
        remove(sprite);

        // assert
        assertNull(this.sprites.getSprites());
    }

    @Test
    void givenTwoSpriteInSprites_whenRemoveTailSprite_thenReturnSpritesWithOnlyTheFirstSprite() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero second = givenHeroAt(1);
        givenSpritesWith(first, second);

        // act
        remove(second);

        // assert
        assertEquals(first, this.sprites.getSprites());
        assertNull(this.sprites.getSprites().getNext());
    }

    @Test
    void givenTwoSpriteInSprites_whenRemoveHeadSprite_thenReturnSpritesWithOnlyTheSecondSprite() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero second = givenHeroAt(1);
        givenSpritesWith(first, second);

        // act
        remove(first);

        // assert
        assertEquals(second, this.sprites.getSprites());
        assertNull(this.sprites.getSprites().getNext());
    }

    @Test
    void givenThreeSpriteInSprites_whenRemoveMiddleSprite_thenReturnSpritesWithOnlyHeadAndTail() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero middle = givenHeroAt(1);
        Hero tail = givenHeroAt(2);
        givenSpritesWith(first, middle, tail);

        // act
        remove(middle);

        // assert
        assertEquals(first, this.sprites.getSprites());
        assertEquals(tail, this.sprites.getSprites().getNext());
    }

    private Hero givenHeroAt(int coordinate) {
        return new Hero(coordinate);
    }

    private void givenSpritesWith(Sprite... sprites) {
        for (Sprite sprite : sprites) {
            this.sprites.add(sprite);
        }
    }

    private void addSprite(Hero sprite) {
        this.sprites.add(sprite);
    }

    private Sprite findSpriteByCoordinate(int coordinate) {
        return this.sprites.findBy(coordinate);
    }

    private void moveSpriteTo(Hero hero, int coordinate) {
        this.sprites.move(hero, coordinate);
    }

    private void remove(Hero secondSprite) {
        this.sprites.remove(secondSprite);
    }
}
