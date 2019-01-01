package controllers;

import static models.Fixture.users;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Collection;
import static models.Fixture.activities;
import static models.Fixture.locations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.User;

public class PacemakerAPIUserTest {

	  private PacemakerAPI pacemaker;

	  @BeforeEach
	  public void setup()
	  {
	    pacemaker = new PacemakerAPI();
	    
	  }
	  
	  @AfterEach
	  public void tearDown()
	  {
	    pacemaker = null;
	  }
	  
	  @Test
	  public void testCreateUser() {
		    assertEquals (0, pacemaker.getUsers().size());
		    pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
		    assertEquals (1, pacemaker.getUsers().size());
		    

	  }

	  @Test
	  public void testGetUsers() {
	    users.forEach(
	        user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
	    Collection<User> returnedUsers = pacemaker.getUsers();
	    assertEquals(users.size(), returnedUsers.size());
	  }
	  
	  @Test
	  public void testDeleteUser() {
	  		assertEquals (0, pacemaker.getUsers().size());
		    pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
		    assertEquals (1, pacemaker.getUsers().size());
		    User user = pacemaker.getUserByEmail("homer@simpson.com");
		    pacemaker.deleteUser(user.id);
		    assertEquals (0, pacemaker.getUsers().size());
    
	  }
	  
	  @Test
	  public void testDeleteUsers() {
	  		assertEquals (0, pacemaker.getUsers().size());
		    users.forEach(
			        user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
		    Collection<User> returnedUsers = pacemaker.getUsers();
		    assertEquals(users.size(), returnedUsers.size());
		    pacemaker.deleteUsers();
		    assertEquals (0, pacemaker.getUsers().size());
    
	  }
	  
	  @Test
	  public void testgetuser() {
		  User user = users.get(0);
		  User crtuser= pacemaker.createUser(user.firstname, user.lastname, user.email, user.password);
		  assertEquals (user, pacemaker.getUser(crtuser.id));
	  }

}
