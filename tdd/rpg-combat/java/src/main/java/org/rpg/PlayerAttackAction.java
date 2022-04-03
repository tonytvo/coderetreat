package org.rpg;

public class PlayerAttackAction implements AttackAction {
    private final Player attackingPlayer;
    private final Player target;
    private final DistanceCalculator distanceCalculator;
    private final int damage;

    public PlayerAttackAction(Player attackingPlayer,
                              Player target,
                              DistanceCalculator distanceCalculator,
                              int damage) {
        this.attackingPlayer = attackingPlayer;
        this.target = target;
        this.distanceCalculator = distanceCalculator;
        this.damage = damage;
    }

    @Override
    public void attack() {
        if (attackingPlayer.isAlliedWith(target)) {
            return;
        }

        if (!distanceCalculator.isWithinRange(attackingPlayer.getPosition(), target.getPosition(), attackingPlayer.maxRange())) {
            return;
        }

        int calculatedDamange = reduceDamageByHalfIfAttackedCharacterIs5LevelHigher(attackingPlayer, target, damage);
        target.receiveDamage(calculatedDamange);

    }

    private int reduceDamageByHalfIfAttackedCharacterIs5LevelHigher(Player attackingPlayer,
                                                                    Player target,
                                                                    int damage) {
        if (attackingPlayer.shouldReduceDamageForCharacter(target)) {
            return halfOf(damage);
        }

        if (attackingPlayer.shouldIncreaseDamageForCharacter(target)) {
            return Math.toIntExact(Math.round(damage * 1.5));
        }

        return damage;
    }

    private int halfOf(int damage) {
        return damage / 2;
    }
}
