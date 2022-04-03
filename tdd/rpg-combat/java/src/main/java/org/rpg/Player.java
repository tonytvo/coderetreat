package org.rpg;

class Player {
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
    }
}
