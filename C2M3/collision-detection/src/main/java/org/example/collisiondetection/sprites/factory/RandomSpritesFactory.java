package org.example.collisiondetection.sprites.factory;

import org.example.collisiondetection.sprites.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSpritesFactory implements SpritesFactory {

    @Override
    public void create(Sprites sprites) {
        // generate random unique coordinates in order to make sure no two sprites will be on the same spot.
        generateTenRandomUniqueCoordinates().stream()
                .map(this::generateSpriteOnCoordinate)
                .forEachOrdered(sprites::add);
    }

    private List<Integer> generateTenRandomUniqueCoordinates() {
        List<Integer> collection = IntStream.range(0, 30).boxed().collect(Collectors.toList());
        Collections.shuffle(collection);
        return collection.subList(0, 11);
    }

    private Sprite generateSpriteOnCoordinate(Integer coordinate) {
        if (coordinate % 3 == 0) {
            return new Hero(coordinate);
        } else if (coordinate % 3 ==1) {
            return new Water(coordinate);
        } else {
            return new Fire(coordinate);
        }
    }
}
