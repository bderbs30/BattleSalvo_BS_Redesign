package cs3500.pa04.model.json;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.other.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordJsonTest {

  ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
  }

  @Test
  public void testCoordJson() {
    Coord coord = new Coord(4, 2);
    CoordJson coordJson = new CoordJson(coord.getX(), coord.getY());
    try {
      String coordString = mapper.writeValueAsString(coordJson);
      String expectedCoord = "{\"x\":4,\"y\":2}";
      assertEquals(expectedCoord, coordString);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}