package org.rpg;

class MeleeFighter extends CharacterPlayer {
    public MeleeFighter(int initialLevel) {
        super(initialLevel, (coord1, coord2, allowedRange) -> true);
    }

    @Override
    public int maxRange() {
        return 2;
    }
}
