package org.rpg;

public class CharacterPlayer {

    public static final int MAX_HEALTH = 1000;
    public static final int INITIAL_LEVEL = 1;
    private final int level;
    private int health;

    public CharacterPlayer(int initialLevel) {
        health = MAX_HEALTH;
        level = initialLevel;
    }

    public int health() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int level() {
        return level;
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
        int calculatedDamange = reduceDamageByHalfIfAttackedCharacterIs5LevelHigher(damage, attackedCharacter);
        attackedCharacter.receiveDamage(calculatedDamange);
    }

    private int reduceDamageByHalfIfAttackedCharacterIs5LevelHigher(int damage, CharacterPlayer attackedCharacter) {
        if (shouldReduceDamangeForCharacter(attackedCharacter)) {
            return halfOf(damage);
        } else if (shouldIncreaseDamageForCharacter(attackedCharacter)) {
            return Math.toIntExact(Math.round(damage * 1.5));
        }
        else {
            return damage;
        }
    }

    private int halfOf(int damage) {
        return damage / 2;
    }

    private boolean shouldReduceDamangeForCharacter(CharacterPlayer attackedCharacter) {
        return this.level <= attackedCharacter.level() - 5;
    }

    private boolean shouldIncreaseDamageForCharacter(CharacterPlayer attackedCharacter) {
        return this.level >= attackedCharacter.level() + 5;
    }

    private void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
