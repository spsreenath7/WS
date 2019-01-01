package models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static models.Fixture.activities;
import static models.Fixture.locations;

class ActivityTest {

  Activity test = new Activity ("jog",  "school", 5.2);

  @Test
  public void testCreate()
  {
    assertEquals ("jog",          test.type);
    assertEquals ("school",        test.location);
    assertEquals (5.2,   test.distance);    
  }

  @Test
  public void testIds()
  {
    Set<String> ids = new HashSet<>();
    for (Activity activity : activities)
    {
      ids.add(activity.id);
    }
    assertEquals (activities.size(), ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("Activity{" + "jog, school, 5.2, []}", test.toString());
  }
  
  @Test
  public void testEquals()
  {
	  Activity act = new Activity(activities.get(0).type, activities.get(0).location, activities.get(0).distance);
	  assertEquals(act, activities.get(0));
	  assertNotEquals( act, "str");
//	  assertTrue( act.hashCode()==activities[0].hashCode() );
	  
  }

}