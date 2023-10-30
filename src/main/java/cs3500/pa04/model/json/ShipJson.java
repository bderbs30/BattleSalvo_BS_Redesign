package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.other.Coord;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "coord": {"x": 0, "y": 0},
 * "length": 4,
 * "direction": "VERTICAL"
 * }
 * </code>
 * </p>
 */
public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}
