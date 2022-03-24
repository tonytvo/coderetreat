package org.rpg;

public class CharacterPlayer {

    public static final int INITIAL_HEALTH = 1000;
    private int health;

    public CharacterPlayer() {
        health = INITIAL_HEALTH;
    }

    public int health() {
        return health;
    }

    public void receiveDamage(int damage) {
        health -= damage;
    }
}
