package org.rpg;

public interface DistanceCalculator {
    boolean isWithinRange(Coord position1, Coord position2, int maxRange);
}
