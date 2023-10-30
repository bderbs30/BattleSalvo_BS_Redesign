package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.other.GameType;

/**
 * JSON format of this record:
 *
 * @param githubUsername the github username of the player
 * @param gameType       the type of game the player wants to play
 */
public record JoinJson(
    @JsonProperty("name") String githubUsername,
    @JsonProperty("game-type") GameType gameType) {
}
