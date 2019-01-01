package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Activity;
import models.Location;
import models.User;

public class PacemakerAPILocationTest {
	
	  private PacemakerAPI pacemaker;
	  private User user;
	  private Activity act;

	  @BeforeEach
	  public void setup()
	  {
	    pacemaker = new PacemakerAPI();
	    pacemaker.createUser("bart", "simpson", "bart@simpson.com", "secret");
	    user = pacemaker.getUserByEmail("bart@simpson.com");
	    act= pacemaker.createActivity(user.id, "walk", "bar", 1.0);
	  }
	  
	  @AfterEach
	  public void tearDown()
	  {
	    pacemaker = null;
	  }
	  
	  @Test
	  public void testAddLocation() {
		  assertEquals (0, act.route.size());
		  
		  pacemaker.addLocation(act.id, 5.2, 7.0);
		  Location location = new Location(5.2, 7.0);
		  assertEquals(location,act.route.get(0));
		  
		  pacemaker.addLocation(act.id, 4.3, 5.2);
		  assertEquals (2, act.route.size());
		  		  
	  }

}
