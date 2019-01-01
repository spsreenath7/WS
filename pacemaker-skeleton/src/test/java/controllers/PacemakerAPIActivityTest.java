package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Activity;
import models.User;

public class PacemakerAPIActivityTest {

	  private PacemakerAPI pacemaker;
	  private User user;

	  @BeforeEach
	  public void setup()
	  {
	    pacemaker = new PacemakerAPI();
	    pacemaker.createUser("bart", "simpson", "bart@simpson.com", "secret");
	    user = pacemaker.getUserByEmail("bart@simpson.com");
	  }
	  
	  @AfterEach
	  public void tearDown()
	  {
	    pacemaker = null;
	  }
	  
	  @Test
	  public void testCreateActivity() {
		  
		  assertEquals (0, user.activities.size());
		  Activity act = new Activity("walk", "bar", 1.0);
		  Activity useract = pacemaker.createActivity(user.id, "walk", "bar", 1.0);
		  assertEquals (1,pacemaker.getActivities(user.id).size());
		  assertEquals (act,useract);
		  
	  }
	  
	  @Test
	  public void testGetActivities() {
		  
		  assertEquals (0, user.activities.size());
		  pacemaker.createActivity(user.id, "walk", "bar", 1.0);
		  pacemaker.createActivity(user.id, "run", "work", 2.0);
		  pacemaker.createActivity(user.id, "cycle", "school", 3.0);
		  assertEquals (3,pacemaker.getActivities(user.id).size());
	  }
	  
	  @Test
	  public void testGetActivity() {
		  Activity act = new Activity("walk", "bar", 1.0);
		  Activity useract = pacemaker.createActivity(user.id, "walk", "bar", 1.0);
		  assertEquals(act, pacemaker.getActivity(useract.id));
	  }
	  
	  @Test
	  public void testListActivitiesSorted() {
		  
		  assertEquals (0, user.activities.size());
		  pacemaker.createActivity(user.id, "run", "school", 4.0);
		  pacemaker.createActivity(user.id, "walk", "work", 5.0);
		  pacemaker.createActivity(user.id, "cycle", "bar", 3.0);
		  
		  Activity act;
		  
		  act = pacemaker.listActivities(user.id, "type").get(0);
		  assertEquals("cycle",act.type);
		  
		  act = pacemaker.listActivities(user.id, "location").get(0);
		  assertEquals("bar",act.location);
		  
		  act = pacemaker.listActivities(user.id, "distance").get(0);
		  assertEquals(3.0,act.distance);
	  }
	  
	  @Test
	  public void testDeleteActivities() {
		  User user = pacemaker.getUserByEmail("bart@simpson.com");
		  assertEquals (0, user.activities.size());
		  
		  pacemaker.createActivity(user.id, "run", "bar", 4.0);
		  pacemaker.createActivity(user.id, "walk", "work", 5.0);
		  pacemaker.createActivity(user.id, "cycle", "school", 3.0);
		  assertEquals (3,pacemaker.getActivities(user.id).size());
		  
		  pacemaker.deleteActivities(user.id);
		  assertEquals (0, user.activities.size());
	  }
	  
}
