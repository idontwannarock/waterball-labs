package org.example.collisiondetection;

import org.example.collisiondetection.collision.*;
import org.example.collisiondetection.sprites.factory.RandomSpritesFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        var world = new World(new RandomSpritesFactory(),
                new WaterFireCollisionHandler(
                        new WaterWaterCollisionHandler(
                                new FireFireCollisionHandler(
                                        new HeroFireCollisionHandler(
                                                new HeroWaterCollisionHandler(
                                                        new HeroHeroCollisionHandler(null)))))));

        var scanner = new Scanner(System.in);
        while (true) {
            printInstruction(world);
            String line = scanner.nextLine();
            if (isExit(line)) {
                break;
            } else if (isValidCoordinates(line)) {
                List<Integer> coordinates = extractCoordinates(line);
                try {
                    world.move(coordinates.get(0), coordinates.get(1));
                } catch (IllegalArgumentException e) {
                    System.out.println("There is no Sprite on your first coordinate " + coordinates.get(0) + " to move.");
                }
            } else {
                System.out.println("Your input is not valid.");
            }
            System.out.println();
        }
    }

    private static void printInstruction(World world) {
        System.out.print("Here is the Sprites in the world: ");
        world.printWorld();
        System.out.println("Please enter two coordinates separated by whitespace " +
                "meaning you want to move the Sprite on the first coordinate to the second.");
        System.out.println("For example, 1 10, meaning you want to move the Sprite on coordinate 1 to 10.");
        System.out.println("Or enter \"exit\" if you want to leave to game:");
    }

    private static boolean isExit(String line) {
        return "exit".equalsIgnoreCase(line);
    }

    private static boolean isValidCoordinates(String line) {
        if (line == null || line.isBlank()) return false;

        String[] coordinates = line.split("\\s+");
        if (coordinates.length != 2) return false;

        try {
            int firstCoordinate = Integer.parseInt(coordinates[0]);
            int secondCoordinate = Integer.parseInt(coordinates[1]);
            return firstCoordinate >= 0 && firstCoordinate < 30 && secondCoordinate >=0 && secondCoordinate < 30;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static List<Integer> extractCoordinates(String line) {
        return Arrays.stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
