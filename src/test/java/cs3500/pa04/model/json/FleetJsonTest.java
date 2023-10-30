package cs3500.pa04.model.json;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipAdapter;
import cs3500.pa04.model.ship.ShipType;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FleetJsonTest {

  ObjectMapper mapper;

  Ship ship1;
  Ship ship2;
  Ship ship3;
  List<ShipAdapter> fleet;

  List<Coord> ship1Coords;
  List<Coord> ship2Coords;
  List<Coord> ship3Coords;

  ShipAdapter shipAdapter1;
  ShipAdapter shipAdapter2;
  ShipAdapter shipAdapter3;


  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
    this.ship1Coords = List.of(new Coord(4, 4), new Coord(5, 4),
        new Coord(6, 4), new Coord(7, 4));
    this.ship2Coords = List.of(new Coord(1, 0), new Coord(1, 1),
        new Coord(1, 2), new Coord(1, 3),
        new Coord(1, 4));
    this.ship3Coords =
        List.of(new Coord(2, 0), new Coord(2, 1), new Coord(2, 2),
            new Coord(2, 3), new Coord(2, 4), new Coord(2, 5));
    this.ship1 = new Ship(ShipType.DESTROYER, ship1Coords);
    this.ship2 = new Ship(ShipType.BATTLESHIP, ship2Coords);
    this.ship3 = new Ship(ShipType.CARRIER, ship3Coords);

    this.shipAdapter1 = new ShipAdapter(ship1);
    this.shipAdapter2 = new ShipAdapter(ship2);
    this.shipAdapter3 = new ShipAdapter(ship3);

    this.fleet = List.of(shipAdapter1, shipAdapter2, shipAdapter3);


  }

  @Test
  public void testFleetJson() {
    FleetJson fleetJson = new FleetJson(fleet);
    try {
      StringBuilder fleetString = new StringBuilder(mapper.writeValueAsString(fleetJson));
      StringBuilder expected = new StringBuilder("{\"fleet\":["
          + "{\"coord\":{\"x\":4,\"y\":4},\"length\":4,\"direction\":\"HORIZONTAL\"},"
          + "{\"coord\":{\"x\":1,\"y\":0},\"length\":5,\"direction\":\"VERTICAL\"},"
          + "{\"coord\":{\"x\":2,\"y\":0},\"length\":6,\"direction\":\"VERTICAL\"}]}");

      assertEquals(expected.toString(), fleetString.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}