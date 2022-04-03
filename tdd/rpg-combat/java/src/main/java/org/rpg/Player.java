package org.rpg;

class Player {
    protected static final int INITIAL_HEALTH = 1000;
    private final int _health;

    public Player() {
        _health = INITIAL_HEALTH;
    }

    public int health() {
        return _health;
    }
}
