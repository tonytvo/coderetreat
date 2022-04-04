package org.rpg;

import io.vavr.API;

import static io.vavr.API.$;
import static io.vavr.API.Case;

class CombatRpgApi {
    public Player receiveDamage(Player player, int damage) {
        Player updatedPlayer = API.Match(player.health() - damage).of(
                Case($(health -> health > 0), Player::new),
                Case($(), health -> new Player(0))
        );
        return updatedPlayer;
    }
}
