package org.rpg;

class RangedFighter extends CharacterPlayer {
    public RangedFighter(int initialLevel) {
        super(initialLevel);
    }

    @Override
    public int maxRange() {
        return 20;
    }
}
