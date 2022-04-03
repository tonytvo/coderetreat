package org.rpg;

public class AttackActionFactory {
    public AttackAction createAttackAction(Player attackingPlayer,
                                           Player target,
                                           DistanceCalculator distanceCalculator,
                                           int damage) {
        if (target instanceof Player) {
            return new PlayerAttackAction(attackingPlayer, target, distanceCalculator, damage);
        }
        throw new UnsupportedOperationException("not implemented yet");
    }
}
