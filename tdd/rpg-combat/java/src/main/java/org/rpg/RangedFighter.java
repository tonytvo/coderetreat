package org.rpg;

class RangedFighter extends Player {
    public RangedFighter(int initialLevel, DistanceCalculator distanceCalculator) {
        super(initialLevel, distanceCalculator);
    }

    @Override
    public int maxRange() {
        return 20;
    }
}
