package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RpgCombatAcceptanceTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Player.INITIAL_HEALTH + 1;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    public void all_players_has_health_at_1000_when_created() {
        assertThat(player.health()).isEqualTo(Player.INITIAL_HEALTH);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = player.health();

        player.receiveDamage(1);

        assertThat(player.health()).isEqualTo(currentHealth-1);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        player.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(player.health()).isEqualTo(0);
    }

    @Test
    public void when_when_health_becomes_zero_character_dies() {
        player.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(player.isAlive()).isEqualTo(false);
    }
}