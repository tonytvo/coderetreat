package org.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.rpg.CharacterPlayer.INITIAL_HEALTH;
import static org.rpg.CharacterPlayer.INITIAL_LEVEL;

public class CharacterPlayerTest {
    private static final int DAMAGE_EXCEEDING_HEALTH = CharacterPlayer.INITIAL_HEALTH + 1;

    private CharacterPlayer character;

    @BeforeEach
    void setUp() {
        character = new CharacterPlayer();
    }

    @Test
    public void has_health_at_1000_when_created() {
        CharacterPlayer characterPlayer = new CharacterPlayer();

        assertThat(characterPlayer.health()).isEqualTo(INITIAL_HEALTH);
    }

    @Test
    public void has_level_at_1_when_created() {
        CharacterPlayer characterPlayer = new CharacterPlayer();

        assertThat(characterPlayer.level()).isEqualTo(INITIAL_LEVEL);
    }

    @Test
    public void loses_health_when_receiving_damage() {
        int currentHealth = character.health();

        character.receiveDamage(1);

        assertThat(character.health()).isEqualTo(currentHealth-1);
    }

    @Test
    public void when_damage_received_exceeds_current_health_health_becomes_zero() {
        character.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.health()).isEqualTo(0);
    }

    @Test
    public void when_when_health_becomes_zero_character_dies() {
        character.receiveDamage(DAMAGE_EXCEEDING_HEALTH);

        assertThat(character.isAlive()).isEqualTo(false);
    }
}