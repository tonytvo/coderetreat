package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.rpg.CharacterPlayer.INITIAL_LEVEL;

public class CharacterPlayerTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = CharacterPlayer.MAX_HEALTH + 1;

    private CharacterPlayer target;
    private CharacterPlayer attacker;

    @BeforeEach
    void setUp() {
        target = new CharacterPlayer(INITIAL_LEVEL);
        attacker = new CharacterPlayer(INITIAL_LEVEL);
    }

    @Test
    public void has_health_at_1000_when_created() {
        assertThat(target.health()).isEqualTo(CharacterPlayer.MAX_HEALTH);
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

        assertThat(target.health()).isEqualTo(CharacterPlayer.MAX_HEALTH);
    }

    @Test
    public void dead_character_can_not_be_healed() {
        attacker.attack(DAMAGE_EXCEEDING_HEALTH, target);

        target.heal(2);

        assertThat(target.isAlive()).isEqualTo(false);
    }

    @Test
    public void damage_is_reduced_by_50_percent_when_target_level_is_at_least_five_levels_above_attacker() {
        attacker = new CharacterPlayer(1);
        target = new CharacterPlayer(6);

        attacker.attack(2, target);

        assertThat(target.health()).isEqualTo(CharacterPlayer.MAX_HEALTH - 1);
    }

    @Test
    public void damage_is_increased_by_50_percent_when_attacker_level_is_at_least_five_levels_above_target() {
        target = new CharacterPlayer(1);

        attacker = new CharacterPlayer(1 + 5);
        attacker.attack(2, target);

        assertThat(target.health()).isEqualTo(CharacterPlayer.MAX_HEALTH - 3);
    }
}