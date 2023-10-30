package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "x": 4,
 * "y": 2
 * }
 * </code>
 * </p>
 *
 * @param x the x coordinate
 * @param y the y coordinate
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
