package org.rpg;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RpgCombatAcceptanceTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Player.INITIAL_HEALTH + 1;

    @Test
    public void all_players_has_health_at_1000_when_created() {
        Player player = new Player();

        assertThat(player.health()).isEqualTo(Player.INITIAL_HEALTH);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        Player character = new Player();

        int currentHealth = character.health();

        character.receiveDamage(1);

        assertThat(character.health()).isEqualTo(currentHealth-1);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        Player character = new Player();

        character.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.health()).isEqualTo(0);
    }
}