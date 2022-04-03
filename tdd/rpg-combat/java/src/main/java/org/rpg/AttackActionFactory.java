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

    private static class AttackThingAction implements AttackAction {
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
}
