package org.rpg;

public class PlayerBuilder {
    private int initialLevel;
    private DistanceCalculator distanceCalculator = (coord1, coord2, allowedRange) -> true;
    private boolean isMeleeFighter = false;
    private boolean isRangedFighter = false;

    public PlayerBuilder setInitialLevel(int initialLevel) {
        this.initialLevel = initialLevel;
        return this;
    }

    public PlayerBuilder setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
        return this;
    }

    public Player createPlayer() {
        if (isMeleeFighter) {
            return new MeleeFighter(initialLevel);
        }
        if (isRangedFighter) {
            return new RangedFighter(initialLevel, distanceCalculator);
        }
        return new Player(initialLevel, distanceCalculator);
    }

    public PlayerBuilder forMeleeFighter() {
        isMeleeFighter = true;
        return this;
    }

    public PlayerBuilder forRangedFighter() {
        isRangedFighter = true;
        return this;
    }
}