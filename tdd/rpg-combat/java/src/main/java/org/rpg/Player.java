package org.rpg;

class Player {
    protected static final int INITIAL_LEVEL = 0;
    protected static final int INITIAL_HEALTH = 1000;
    private final int _health;

    public Player(int initialHealth) {
        _health = initialHealth;
    }

    public int health() {
        return _health;
    }

    public boolean isAlive() {
        return _health > 0;
    }

    public int level() {
        return INITIAL_LEVEL;
    }
}
