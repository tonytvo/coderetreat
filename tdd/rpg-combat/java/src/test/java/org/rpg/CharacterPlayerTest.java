package org.rpg;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.rpg.CharacterPlayer.INITIAL_HEALTH;

public class CharacterPlayerTest {

    @Test
    public void has_health_at_1000_when_created() {
        CharacterPlayer characterPlayer = new CharacterPlayer();

        assertThat(characterPlayer.health()).isEqualTo(INITIAL_HEALTH);
    }

}