package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.rpg.Player.INITIAL_LEVEL;

public class RpgCombatAcceptanceTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = Player.INITIAL_HEALTH + 1;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(Player.INITIAL_HEALTH);
    }

    @Test
    public void all_players_has_health_at_1000_when_created() {
        assertThat(player.health()).isEqualTo(Player.INITIAL_HEALTH);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = player.health();

        receiveDamage(player, 1);

        assertThat(player.health()).isEqualTo(currentHealth-1);
    }

    private void receiveDamage(Player player, int damage) {
        player.receiveDamage(damage);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        receiveDamage(player, DAMAGE_EXCEEDING_HEALTH);

        assertThat(player.health()).isEqualTo(0);
    }

    @Test
    public void when_when_health_becomes_zero_character_dies() {
        receiveDamage(player, DAMAGE_EXCEEDING_HEALTH);

        assertThat(player.isAlive()).isEqualTo(false);
    }

    @Test
    public void has_level_at_1_when_created() {
        assertThat(player.level()).isEqualTo(INITIAL_LEVEL);
    }
}