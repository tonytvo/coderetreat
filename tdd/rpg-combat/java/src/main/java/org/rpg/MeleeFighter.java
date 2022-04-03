package org.rpg;

public class MeleeFighter extends Player {
    public MeleeFighter(int initialLevel) {
        super(initialLevel);
    }

    @Override
    public int maxRange() {
        return 2;
    }
}
