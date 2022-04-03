package org.rpg;

class Player {
    protected static final int INITIAL_LEVEL = 0;
    protected static final int INITIAL_HEALTH = 1000;
    private int _health;

    public Player() {
        _health = INITIAL_HEALTH;
    }

    public int health() {
        return _health;
    }

    public void receiveDamage(int damage) {
        _health -= damage;
        if (_health < 0) {
            _health = 0;
        }
    }

    public boolean isAlive() {
        return _health > 0;
    }

    public int level() {
        return INITIAL_LEVEL;
    }
}
