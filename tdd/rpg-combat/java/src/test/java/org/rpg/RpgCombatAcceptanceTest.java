package org.rpg;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RpgCombatAcceptanceTest {

    private static final int INITIAL_HEALTH = 1000;

    @Test
    public void all_players_has_health_at_1000_when_created() {
        Player player = new Player();

        assertThat(player.health()).isEqualTo(INITIAL_HEALTH);
    }

    private static class Player {
        public int health() {
            return INITIAL_HEALTH;
        }
    }
}