package org.rpg;

class RangedFighter extends CharacterPlayer {
    public RangedFighter(int initialLevel, DistanceCalculator distanceCalculator) {
        super(initialLevel, distanceCalculator);
    }

    @Override
    public int maxRange() {
        return 20;
    }
}
