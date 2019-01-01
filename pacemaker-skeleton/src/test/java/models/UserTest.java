package models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static models.Fixtures.users;

class UserTest {

  User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");

  @Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstname);
    assertEquals ("simpson",             homer.lastname);
    assertEquals ("homer@simpson.com",   homer.email);   
    assertEquals ("secret",              homer.password);   
  }

  @Test
  public void testIds()
  {
    Set<String> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.size(), ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("User{homer, simpson, secret, homer@simpson.com, {}, []}", homer.toString());
  }
  
  @Test
  public void testEquals()
  {
    User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");
    User homer2 = new User ("homer", "simpson", "homer@simpson.com",  "secret"); 
    User bart   = new User ("bart", "simpson", "bartr@simpson.com",  "secret"); 

    assertSame(homer, homer);
    assertEquals(homer, homer2);
    assertNotSame(homer, homer2);
    assertNotEquals(homer, bart);
    
    
//    assertTrue(homer.hashCode() == homer2.hashCode());
    
  }

}