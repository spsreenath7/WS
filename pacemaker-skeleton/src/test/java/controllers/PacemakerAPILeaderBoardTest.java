package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Activity;
import models.Location;
import models.Message;
import models.User;

public class PacemakerAPILeaderBoardTest {
	
	private PacemakerAPI pacemaker;
	  private User user,friend1,friend2;
	  private Activity a1,a2,a3,a4,a5,a6;

	  @BeforeEach
	  public void setup()
	  {
	    pacemaker = new PacemakerAPI();
	    user = pacemaker.createUser("bart", "simpson", "bart@simpson.com", "secret");
	    
	    friend1 = pacemaker.createUser("maggie", "simpson", "maggie@simpson.com", "secret");
	    friend2= pacemaker.createUser("lisa", "simpson", "lisa@simpson.com", "secret");
	    pacemaker.follow(user.id, friend1.email);
	    pacemaker.follow(user.id, friend2.email);
	    
	    a1 = pacemaker.createActivity(friend1.id, "run", "gym", 2.5);
	    a2 = pacemaker.createActivity(friend1.id, "walk", "work", 1.6);
	    a3 = pacemaker.createActivity(friend1.id, "jog", "ground", 3.4);
	    
	    a4 = pacemaker.createActivity(friend2.id, "run", "gym", 3.2);
	    a5 = pacemaker.createActivity(friend2.id, "walk", "work", 2.5);
	    a6 = pacemaker.createActivity(friend2.id, "jog", "ground", 1.4);
	    
	  }
	  
	  @AfterEach
	  public void tearDown()
	  {
	    pacemaker = null;
	  }
	  
	  @Test
	  public void testDistanceLeaderBoard() {
		  
		  double friend1Distance = a1.distance + a2.distance +a3.distance; // 7.5
		  double friend2Distance = a4.distance + a5.distance +a6.distance; // 7.1
		  assertEquals(friend1Distance, pacemaker.distanceLeaderBoard(user.id).get(0).distance);
		  
		  Activity act = pacemaker.createActivity(friend2.id, "cycle", "bar", 1.0);
		  friend2Distance += act.distance; //8.1
		  assertEquals(friend2.email, pacemaker.distanceLeaderBoard(user.id).get(0).email);
		  
	  }
	  
	  @Test
	  public void testDistanceLeaderBoardByType() {
		  

		  assertEquals(a4.distance, pacemaker.distanceLeaderBoardByType(user.id, "run").get(0).distance);
		  assertEquals(friend2.email, pacemaker.distanceLeaderBoardByType(user.id, "run").get(0).email);
		  
		  assertEquals(a3.distance, pacemaker.distanceLeaderBoardByType(user.id, "jog").get(0).distance);
		  assertEquals(friend1.email, pacemaker.distanceLeaderBoardByType(user.id, "jog").get(0).email);
		  
		  Activity act = pacemaker.createActivity(friend2.id, "jog", "bar", 4.0);
		  assertEquals(act.distance+a6.distance, pacemaker.distanceLeaderBoardByType(user.id, "jog").get(0).distance);
		  assertEquals(friend2.email, pacemaker.distanceLeaderBoardByType(user.id, "jog").get(0).email); 
		  
	  }
	  
	  @Test
	  public void testLocationLeaderBoard() {

		  assertEquals(0, pacemaker.locationLeaderBoard(user.id, "5.2", "7.0").size());
		  
		  pacemaker.addLocation(a1.id, Double.parseDouble("7.0"), Double.parseDouble("5.2"));
		  assertEquals(1, pacemaker.locationLeaderBoard(user.id, "5.2", "7.0").size());
		  
		  pacemaker.addLocation(a5.id, Double.parseDouble("7.0"), Double.parseDouble("5.2"));
		  assertEquals(2, pacemaker.locationLeaderBoard(user.id, "5.2", "7.0").size());

	  }

}
