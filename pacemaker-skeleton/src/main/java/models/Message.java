package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.util.UUID;

import com.google.common.base.Objects;


public class Message {
	
	public String sender;
	public String message;
	public String reciever;
	  
	public Message(String reciever, String sender, String message) {
		  this.reciever = reciever;
		  this.sender = sender;
		  this.message = message;
	}
		  

	  @Override
	  public boolean equals(final Object obj) {
	    if (obj instanceof Message) {
	      final Message other = (Message) obj;
	      return Objects.equal(sender, other.sender)
	          && Objects.equal(message, other.message)
	          && Objects.equal(reciever, other.reciever);
	    } else {
	      return false;
	    }
	  }

	  @Override
	  public String toString() {
	    return toStringHelper(this).addValue(sender)
	        .addValue(message)
	        .addValue(reciever)
	        .toString();
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hashCode(this.sender, this.message, this.reciever);
	  }

}
