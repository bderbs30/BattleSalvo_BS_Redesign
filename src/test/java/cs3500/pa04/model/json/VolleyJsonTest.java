package cs3500.pa04.model.json;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.other.Coord;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class VolleyJsonTest {

  ObjectMapper mapper;
  List<Coord> volley1;

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
    this.volley1 = List.of(new Coord(4, 2), new Coord(7, 1));
  }

  @Test
  public void testVolleyJson() {
    CoordinatesJson volleyJson = new CoordinatesJson(volley1);
    try {
      String volleyString = mapper.writeValueAsString(volleyJson);
      String expectedVolley = "{\"coordinates\":[{\"x\":4,\"y\":2},{\"x\":7,\"y\":1}]}";
      assertEquals(expectedVolley, volleyString);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}