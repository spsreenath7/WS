package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static models.Fixture.locations;

class LocationTest {

  @Test
  public void testCreate()
  {
    assertEquals (0.01, 23.3, locations.get(0).latitude);
    assertEquals (0.01, 33.3, locations.get(0).longitude);
  }

  @Test
  public void testIds()
  {
    assertNotEquals(locations.get(0).id, locations.get(1).id);
  }

  @Test
  public void testToString()
  {
    assertEquals ("Location{" + "23.3, 33.3}", locations.get(0).toString());
  }
  
  @Test
  public void testEquals()
  {
	  Location l1 = new Location(locations.get(0).latitude, locations.get(0).longitude);
	  assertEquals(l1, locations.get(0));
	  assertNotEquals( l1, "str");
//	  assertTrue( l1.hashCode()==locations[0].hashCode() );
  }
}