package org.rpg;

class MeleeFighter extends CharacterPlayer {
    public MeleeFighter(int initialLevel) {
        super(initialLevel);
    }

    @Override
    public int maxRange() {
        return 2;
    }
}
