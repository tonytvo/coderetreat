package org.rpg;

import com.google.common.collect.Sets;

import java.util.*;

public class Player implements Target {

    public static final int MAX_HEALTH = 1000;
    public static final int INITIAL_LEVEL = 1;
    public static final int MAX_RANGE = 1;

    private final int level;

    private final Coord position;
    private int health;
    private Set<Faction> factionsJoined;

    public Player(int initialLevel) {
        health = MAX_HEALTH;
        level = initialLevel;
        position = new Coord();
        factionsJoined = new HashSet<>();
    }

    public Coord getPosition() {
        return position;
    }

    @Override
    public int health() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int level() {
        return level;
    }

    public void receiveHealing(int healing) {
        if (!isAlive()) {
            return;
        }

        health += healing;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public void heal(Player target, int healing) {
        if (isAlliedWith(target)) {
            target.receiveHealing(healing);
        }
    }

    public int maxRange() {
        return MAX_RANGE;
    }

    public void join(Faction factionToJoin) {
        factionsJoined.add(factionToJoin);
    }

    public Collection<Faction> factionsJoined() {
        return factionsJoined;
    }

    public void leave(Faction factionToLeave) {
        factionsJoined.remove(factionToLeave);
    }

    public boolean isAlliedWith(Player anotherPlayer) {
        Sets.SetView<Faction> intersection = Sets.intersection(factionsJoined, Set.copyOf(anotherPlayer.factionsJoined()));
        return !intersection.isEmpty();
    }

    protected boolean shouldReduceDamageForCharacter(Player attackedCharacter) {
        return this.level <= attackedCharacter.level() - 5;
    }

    protected boolean shouldIncreaseDamageForCharacter(Player attackedCharacter) {
        return this.level >= attackedCharacter.level() + 5;
    }

    @Override
    public void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

}
