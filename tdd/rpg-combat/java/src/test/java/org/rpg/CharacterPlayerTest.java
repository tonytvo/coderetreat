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

    @Test
    public void loses_health_when_receiving_damage() {
        CharacterPlayer character = new CharacterPlayer();

        int currentHealth = character.health();

        character.receiveDamage(1);

        assertThat(character.health()).isEqualTo(currentHealth-1);
    }
}