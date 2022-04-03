package org.rpg;

public class AttackActionFactory {
    public AttackAction createAttackAction(Player attackingPlayer,
                                           Target target,
                                           DistanceCalculator distanceCalculator,
                                           int damage) {

        if (target instanceof Thing) {
            return new AttackThingAction(target, damage);
        }

        if (target instanceof Player) {
            return new PlayerAttackAction(attackingPlayer, (Player) target, distanceCalculator, damage);
        }
        throw new UnsupportedOperationException("not implemented yet");
    }

}
