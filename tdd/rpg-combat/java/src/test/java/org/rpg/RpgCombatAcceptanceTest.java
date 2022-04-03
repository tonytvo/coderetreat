package org.rpg;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RpgCombatAcceptanceTest {

    @Test
    public void all_players_has_health_at_1000_when_created() {
        Player player = new Player();

        assertThat(player.health()).isEqualTo(Player.INITIAL_HEALTH);
    }

}