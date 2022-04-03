package org.rpg;

public class RpgTree implements Thing {

    private int health;

    public RpgTree() {
        health = 2000;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public void receiveDamage(int damage) {
        health -= damage;
    }
}
