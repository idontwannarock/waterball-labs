package org.example.collisiondetection.sprites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpritesTest {

    private Sprites sprites;

    @BeforeEach
    public void setUp() {
        this.sprites = new Sprites();
    }

    @Test
    public void givenEmptySprites_whenAddingOneSprite_thenPlaceGivenSpriteAtHead() {
        // arrange
        Hero sprite = givenHeroAt(0);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.coordinate, this.sprites.head.coordinate);
    }

    @Test
    public void givenOneSpriteInSprites_whenAddingCloserSprite_thenInsertGivenSpriteAtHead() {
        // arrange
        givenSpritesWith(givenHeroAt(1));
        Hero sprite = givenHeroAt(0);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.coordinate, this.sprites.head.coordinate);
    }

    @Test
    public void givenOneSpriteInSprites_whenAddingFurtherSprite_thenAppendGivenSpriteAtTail() {
        // arrange
        givenSpritesWith(givenHeroAt(0));
        Hero sprite = givenHeroAt(1);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.coordinate, this.sprites.head.next.coordinate);
    }

    @Test
    public void givenTwoSpriteInSprites_whenAddingMiddleSprite_thenInsertGivenSpriteInTheMiddle() {
        // arrange
        givenSpritesWith(givenHeroAt(0), givenHeroAt(2));
        Hero sprite = givenHeroAt(1);

        // act
        addSprite(sprite);

        // assert
        assertEquals(sprite.coordinate, this.sprites.head.next.coordinate);
    }

    @Test
    public void givenEmptySprites_whenFindSpriteByCoordinate_thenThrowIllegalArgumentException() {
        // act and assert
        assertThrows(IllegalArgumentException.class, () -> findSpriteByCoordinate(0));
    }

    @Test
    public void givenOneSpriteInSprites_whenFindSpriteOnEmptySpot_thenThrowIllegalArgumentException() {
        // arrange
        givenSpritesWith(givenHeroAt(0));

        // act and assert
        assertThrows(IllegalArgumentException.class, () -> findSpriteByCoordinate(1));
    }

    @Test
    public void givenOneSpriteInSprites_whenFindSpriteOnNonEmptySpot_thenReturnGivenSprite() {
        // arrange
        givenSpritesWith(givenHeroAt(0));

        // act
        Sprite actual = findSpriteByCoordinate(0);

        // assert
        assertEquals(actual.coordinate, this.sprites.head.coordinate);
    }

    @Test
    public void givenOneSpriteInSprites_whenMoveSpriteToAnotherSpot_thenMoveSucceeded() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite);

        // act
        moveSpriteTo(sprite, 1);

        // assert
        assertEquals(1, this.sprites.head.coordinate);
    }

    @Test
    public void givenTwoSpritesInSprites_whenMoveSecondSpriteToFurtherPlace_thenMoveSucceededWithOrderStaysTheSame() {
        // arrange
        Hero sprite = givenHeroAt(1);
        givenSpritesWith(givenHeroAt(0), sprite);

        // act
        moveSpriteTo(sprite, 2);

        // assert
        assertEquals(2, this.sprites.head.next.coordinate);
    }

    @Test
    public void givenTwoSpritesInSprites_whenMoveFirstSpriteToFurtherThenSecond_thenMoveSucceededWithOrderInverted() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite, givenHeroAt(1));

        // act
        moveSpriteTo(sprite, 2);

        // assert
        assertEquals(sprite, this.sprites.head.next);
        assertEquals(2, this.sprites.head.next.coordinate);
    }

    @Test
    public void givenOneSpriteInSprites_whenRemoveOneExistingSprite_thenRemoveSucceeded() {
        // arrange
        Hero sprite = givenHeroAt(0);
        givenSpritesWith(sprite);

        // act
        remove(sprite);

        // assert
        assertNull(this.sprites.head);
    }

    @Test
    public void givenTwoSpriteInSprites_whenRemoveTailSprite_thenReturnSpritesWithOnlyTheFirstSprite() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero second = givenHeroAt(1);
        givenSpritesWith(first, second);

        // act
        remove(second);

        // assert
        assertEquals(first, this.sprites.head);
        assertNull(this.sprites.head.next);
    }

    @Test
    public void givenTwoSpriteInSprites_whenRemoveHeadSprite_thenReturnSpritesWithOnlyTheSecondSprite() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero second = givenHeroAt(1);
        givenSpritesWith(first, second);

        // act
        remove(first);

        // assert
        assertEquals(second, this.sprites.head);
        assertNull(this.sprites.head.next);
    }

    private Hero givenHeroAt(int coordinate) {
        return new Hero(coordinate);
    }

    @Test
    public void givenThreeSpriteInSprites_whenRemoveMiddleSprite_thenReturnSpritesWithOnlyHeadAndTail() {
        // arrange
        Hero first = givenHeroAt(0);
        Hero middle = givenHeroAt(1);
        Hero tail = givenHeroAt(2);
        givenSpritesWith(first, middle, tail);

        // act
        remove(middle);

        // assert
        assertEquals(first, this.sprites.head);
        assertEquals(tail, this.sprites.head.next);
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
