package cs3500.pa04.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.other.Coord;
import java.util.List;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "volley": [
 * {"x": 4, "y": 2},
 * {"x": 7, "y": 1}
 * ]
 * }
 * </code>
 * </p>
 *
 * @param coordinates the list of coordinates
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") List<Coord> coordinates) {

}
