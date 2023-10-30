package cs3500.pa04.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipAdapter;
import cs3500.pa04.model.ship.ShipType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ShipJsonTest {

  ObjectMapper mapper;

  Ship ship1;
  ShipAdapter shipAdapter1;
  List<Coord> ship1Coords;

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
    this.ship1Coords = List.of(new Coord(4, 4), new Coord(5, 4),
        new Coord(6, 4), new Coord(7, 4));
    this.ship1 = new Ship(ShipType.DESTROYER, ship1Coords);
    this.shipAdapter1 = new ShipAdapter(ship1);


  }

  @Test
  public void testShipJson() {
    ShipJson shipJson = new ShipJson(shipAdapter1.getCoord(), shipAdapter1.getLength(),
        shipAdapter1.getDirection().toString());
    try {
      String shipString2 = mapper.writeValueAsString(shipJson);
      String expected = "{\"coord\":{\"x\":4,\"y\":4},\"length\":4,\"direction\":\"HORIZONTAL\"}";
      assertEquals(expected, shipString2);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}