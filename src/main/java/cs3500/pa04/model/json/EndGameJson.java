package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.other.GameResult;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "result": "WIN",
 * "reason": "You sunk all of the opponent's ships!"
 * }
 * </code>
 * </p>
 *
 * @param result the result of the game
 * @param reason the reason for the result
 */
public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {
}
