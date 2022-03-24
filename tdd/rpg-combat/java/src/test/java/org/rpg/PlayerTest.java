package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.rpg.Player.*;

public class PlayerTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Player.MAX_HEALTH + 1;
    private final DistanceCalculator inRangeDistanceCalculator = (coord1, coord2, allowedRange) -> true;
    private final DistanceCalculator outOfRangeDistanceCalculator = (coord1, coord2, allowedRange) -> false;

    private Player target;
    private Player attacker;

    @BeforeEach
    void setUp() {
        target = new org.rpg.PlayerBuilder()
                .setInitialLevel(INITIAL_LEVEL)
                .createPlayer();
        new PlayerBuilder();
        attacker = new org.rpg.PlayerBuilder()
                .setInitialLevel(INITIAL_LEVEL)
                .createPlayer();
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(target.health()).isEqualTo(Player.MAX_HEALTH);
    }

    @Test
    public void has_level_at_1_when_created() {
        assertThat(target.level()).isEqualTo(INITIAL_LEVEL);
    }

    @Test
    public void character_is_alive_when_created() {
        assertThat(target.isAlive()).isEqualTo(true);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = target.health();

        attacker.attack(1, target);

        assertThat(target.health()).isEqualTo(currentHealth-1);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        attacker.attack(DAMAGE_EXCEEDING_HEALTH, target);

        assertThat(target.health()).isEqualTo(0);
    }

    @Test
    public void when_when_health_becomes_zero_character_dies() {
        attacker.attack(DAMAGE_EXCEEDING_HEALTH, target);

        assertThat(target.isAlive()).isEqualTo(false);
    }

    @Test
    public void character_can_be_healed() {
        attacker.attack(2, target);
        int previousHealth = target.health();

        target.heal(1);

        assertThat(target.health()).isEqualTo(previousHealth+1);
    }

    @Test
    public void healing_cannot_raise_health_above_max_health() {
        attacker.attack(1, target);

        target.heal(2);

        assertThat(target.health()).isEqualTo(Player.MAX_HEALTH);
    }

    @Test
    public void dead_character_can_not_be_healed() {
        attacker.attack(DAMAGE_EXCEEDING_HEALTH, target);

        target.heal(2);

        assertThat(target.isAlive()).isEqualTo(false);
    }

    @Test
    public void damage_is_reduced_by_50_percent_when_target_level_is_at_least_five_levels_above_attacker() {
        attacker = new org.rpg.PlayerBuilder().setInitialLevel(1).setDistanceCalculator(inRangeDistanceCalculator).createPlayer();
        new PlayerBuilder();
        target = new org.rpg.PlayerBuilder().setInitialLevel(6).setDistanceCalculator(inRangeDistanceCalculator).createPlayer();

        attacker.attack(2, target);

        assertThat(target.health()).isEqualTo(Player.MAX_HEALTH - 1);
    }

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        target = new org.rpg.PlayerBuilder()
                .setInitialLevel(1)
                .createPlayer();
        attacker = new org.rpg.PlayerBuilder()
                .setInitialLevel(1 + 5)
                .createPlayer();
        attacker.attack(2, target);

        assertThat(target.health()).isEqualTo(Player.MAX_HEALTH - 3);
    }

    @Test
    public void character_has_an_attack_max_range() {
        assertThat(target.maxRange()).isEqualTo(MAX_RANGE);
    }

    @Test
    public void a_melee_fighter_has_an_attack_range_of_2() {
        Player meleeFighter = new PlayerBuilder()
                .forMeleeFighter()
                .createPlayer();

        assertThat(meleeFighter.maxRange()).isEqualTo(2);
    }

    @Test
    public void a_ranged_fighter_has_an_attack_max_range_of_20() {
        Player rangedFighter = new PlayerBuilder()
                .forRangedFighter()
                .createPlayer();

        assertThat(rangedFighter.maxRange()).isEqualTo(20);
    }

    @Test
    public void cannot_attack_when_enemy_is_out_of_range() {
        Player john = new PlayerBuilder()
                .setDistanceCalculator(outOfRangeDistanceCalculator)
                .forRangedFighter()
                .createPlayer();
        Player louis = new PlayerBuilder()
                .setDistanceCalculator(inRangeDistanceCalculator)
                .forRangedFighter()
                .createPlayer();

        john.attack(2, louis);

        assertThat(louis.health()).isEqualTo(MAX_HEALTH);
    }

    @Test
    public void character_can_join_factions() {
        Set<Faction> factions = Set.of(Faction.DEVELOPERS, Faction.SYSOPS);

        target.join(Faction.DEVELOPERS);
        target.join(Faction.SYSOPS);

        assertThat(target.factionsJoined()).isEqualTo(factions);
    }

    @Test
    public void newly_create_character_does_not_belong_to_any_faction() {
        assertThat(target.factionsJoined()).isEqualTo(Collections.emptySet());
    }

    @Test
    public void character_can_leave_a_faction() {
        target.join(Faction.DEVELOPERS);
        target.join(Faction.SYSOPS);

        target.leave(Faction.DEVELOPERS);

        assertThat(target.factionsJoined()).isEqualTo(Set.of(Faction.SYSOPS));
    }

    @Test
    public void character_can_not_join_the_same_faction_twice() {
        target.join(Faction.DEVELOPERS);
        target.join(Faction.SYSOPS);
        target.join(Faction.DEVELOPERS);

        assertThat(target.factionsJoined()).isEqualTo(Set.of(Faction.SYSOPS, Faction.DEVELOPERS));
    }

    @Test
    public void character_is_allied_with_another_character_if_they_belong_to_the_same_faction() {
        target.join(Faction.DEVELOPERS);
        target.join(Faction.SYSOPS);

        Player meleeFighter = new PlayerBuilder().forMeleeFighter().createPlayer();
        meleeFighter.join(Faction.DEVELOPERS);

        assertThat(target.isAlliedWith(meleeFighter)).isEqualTo(true);
    }
}