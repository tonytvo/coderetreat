package org.rpg;

public class CharacterPlayer {

    public static final int MAX_HEALTH = 1000;
    public static final int INITIAL_LEVEL = 1;
    private int health;

    public CharacterPlayer() {
        health = MAX_HEALTH;
    }

    public int health() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int level() {
        return INITIAL_LEVEL;
    }

    public void heal(int healing) {
        if (!isAlive()) {
            return;
        }

        health += healing;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public void attack(int damage, CharacterPlayer attackedCharacter) {
        attackedCharacter.receiveDamage(damage);
    }

    private void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
