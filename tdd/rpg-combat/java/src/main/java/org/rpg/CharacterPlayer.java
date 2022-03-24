package org.rpg;

public class CharacterPlayer {

    public static final int INITIAL_HEALTH = 1000;
    public static final int INITIAL_LEVEL = 1;
    private int health;

    public CharacterPlayer() {
        health = INITIAL_HEALTH;
    }

    public int health() {
        return health;
    }

    public void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int level() {
        return INITIAL_LEVEL;
    }
}
