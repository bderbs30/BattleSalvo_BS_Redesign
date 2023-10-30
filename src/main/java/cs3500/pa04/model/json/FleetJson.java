package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ship.ShipAdapter;
import java.util.List;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "fleet": [
 * {
 * "coord": {"x": 0, "y": 0},
 * "length": 4,
 * "direction": "VERTICAL"
 * },
 * {
 * "coord": {"x": 1, "y": 0},
 * "length": 5,
 * "direction": "VERTICAL"
 * }
 * ]
 * }
 * </code>
 * </p>
 *
 * @param fleet the fleet of ships
 */

public record FleetJson(
    @JsonProperty("fleet") List<ShipAdapter> fleet) {

}
