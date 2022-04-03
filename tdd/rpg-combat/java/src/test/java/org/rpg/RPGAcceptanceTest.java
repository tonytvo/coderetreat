package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.rpg.Player.*;

public class RPGAcceptanceTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Player.MAX_HEALTH + 1;
    private final DistanceCalculator inRangeDistanceCalculator = (coord1, coord2, allowedRange) -> true;
    private final DistanceCalculator outOfRangeDistanceCalculator = (coord1, coord2, allowedRange) -> false;

    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new org.rpg.PlayerBuilder()
                .setInitialLevel(INITIAL_LEVEL)
                .createPlayer();
        new PlayerBuilder();
        player2 = new org.rpg.PlayerBuilder()
                .setInitialLevel(INITIAL_LEVEL)
                .createPlayer();
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(player1.health()).isEqualTo(Player.MAX_HEALTH);
    }

    @Test
    public void has_level_at_1_when_created() {
        assertThat(player1.level()).isEqualTo(INITIAL_LEVEL);
    }

    @Test
    public void character_is_alive_when_created() {
        assertThat(player1.isAlive()).isEqualTo(true);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = player1.health();

        player2.attack(player1, 1);

        assertThat(player1.health()).isEqualTo(currentHealth-1);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        player2.attack(player1, DAMAGE_EXCEEDING_HEALTH);

        assertThat(player1.health()).isEqualTo(0);
    }

    @Test
    public void when_when_health_becomes_zero_character_dies() {
        player2.attack(player1, DAMAGE_EXCEEDING_HEALTH);

        assertThat(player1.isAlive()).isEqualTo(false);
    }

    @Test
    public void character_can_be_healed() {
        player2.attack(player1, 2);
        int previousHealth = player1.health();

        player1.receiveHealing(1);

        assertThat(player1.health()).isEqualTo(previousHealth+1);
    }

    @Test
    public void healing_cannot_raise_health_above_max_health() {
        player2.attack(player1, 1);

        player1.receiveHealing(2);

        assertThat(player1.health()).isEqualTo(Player.MAX_HEALTH);
    }

    @Test
    public void dead_character_can_not_be_healed() {
        player2.attack(player1, DAMAGE_EXCEEDING_HEALTH);

        player1.receiveHealing(2);

        assertThat(player1.isAlive()).isEqualTo(false);
    }

    @Test
    public void damage_is_reduced_by_50_percent_when_target_level_is_at_least_five_levels_above_attacker() {
        player2 = new org.rpg.PlayerBuilder().setInitialLevel(1).setDistanceCalculator(inRangeDistanceCalculator).createPlayer();
        new PlayerBuilder();
        player1 = new org.rpg.PlayerBuilder().setInitialLevel(6).setDistanceCalculator(inRangeDistanceCalculator).createPlayer();

        player2.attack(player1, 2);

        assertThat(player1.health()).isEqualTo(Player.MAX_HEALTH - 1);
    }

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        player1 = new org.rpg.PlayerBuilder()
                .setInitialLevel(1)
                .createPlayer();
        player2 = new org.rpg.PlayerBuilder()
                .setInitialLevel(1 + 5)
                .createPlayer();
        player2.attack(player1, 2);

        assertThat(player1.health()).isEqualTo(Player.MAX_HEALTH - 3);
    }

    @Test
    public void character_has_an_attack_max_range() {
        assertThat(player1.maxRange()).isEqualTo(MAX_RANGE);
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

        john.attack(louis, 2);

        assertThat(louis.health()).isEqualTo(MAX_HEALTH);
    }

    @Test
    public void character_can_join_factions() {
        Set<Faction> factions = Set.of(Faction.DEVELOPERS, Faction.SYSOPS);

        player1.join(Faction.DEVELOPERS);
        player1.join(Faction.SYSOPS);

        assertThat(player1.factionsJoined()).isEqualTo(factions);
    }

    @Test
    public void newly_create_character_does_not_belong_to_any_faction() {
        assertThat(player1.factionsJoined()).isEqualTo(Collections.emptySet());
    }

    @Test
    public void character_can_leave_a_faction() {
        player1.join(Faction.DEVELOPERS);
        player1.join(Faction.SYSOPS);

        player1.leave(Faction.DEVELOPERS);

        assertThat(player1.factionsJoined()).isEqualTo(Set.of(Faction.SYSOPS));
    }

    @Test
    public void character_can_not_join_the_same_faction_twice() {
        player1.join(Faction.DEVELOPERS);
        player1.join(Faction.SYSOPS);
        player1.join(Faction.DEVELOPERS);

        assertThat(player1.factionsJoined()).isEqualTo(Set.of(Faction.SYSOPS, Faction.DEVELOPERS));
    }

    @Test
    public void character_is_allied_with_another_character_if_they_belong_to_the_same_faction() {
        player1.join(Faction.DEVELOPERS);
        player1.join(Faction.SYSOPS);

        Player meleeFighter = new PlayerBuilder().forMeleeFighter().createPlayer();
        meleeFighter.join(Faction.DEVELOPERS);

        assertThat(player1.isAlliedWith(meleeFighter)).isEqualTo(true);
    }

    @Test
    public void character_can_heal_an_ally() {
        player1.join(Faction.SYSOPS);

        Player ally = new PlayerBuilder().createPlayer();
        ally.join(Faction.SYSOPS);

        int inflictedDamage = 10;
        int healing = 5;

        attack(ally, inflictedDamage);
        player1.heal(ally, healing);

        assertThat(ally.health()).isEqualTo(Player.MAX_HEALTH - inflictedDamage + healing);
    }

    @Test
    public void character_cannot_deal_damage_to_an_ally() {
        player1.join(Faction.DEVELOPERS);
        Player ally = new PlayerBuilder().createPlayer();
        ally.join(Faction.DEVELOPERS);
        player1.attack(ally, 10);
        assertThat(ally.health()).isEqualTo(Player.MAX_HEALTH);
    }

    private void attack(Player ally, int inflictedDamage) {
        new PlayerBuilder().createPlayer().attack(ally, inflictedDamage);
    }
}