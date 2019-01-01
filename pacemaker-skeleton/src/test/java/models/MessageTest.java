package models;

import static models.Fixture.activities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MessageTest {
	
	Message message = new Message("bro", "me", "Hello !");

	 @Test
	  public void testCreate()
	  {
	    assertEquals ("me", message.sender);
	    assertEquals ("bro", message.reciever);
	    assertEquals ("Hello !",   message.message);    
	  }


	  @Test
	  public void testToString()
	  {
	    assertEquals ("Message{" + "me, Hello !, bro}", message.toString());
	  }
	  
	  @Test
	  public void testEquals()
	  {
		  Message othermessage = new Message(message.reciever, message.sender, message.message);
		  assertEquals(othermessage, message);
		  assertNotEquals( othermessage, "str");
//		  assertTrue( act.hashCode()==activities[0].hashCode() );
		  
	  }

}
