package org.rpg;

public class AttackThingAction implements AttackAction {
    private final Target target;
    private final int damage;

    public AttackThingAction(Target target, int damage) {
        this.target = target;
        this.damage = damage;
    }

    @Override
    public void attack() {
        target.receiveDamage(damage);
    }
}
