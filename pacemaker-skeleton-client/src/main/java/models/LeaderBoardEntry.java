package models;

public class LeaderBoardEntry {



	public String firstname;
	  public String lastname;
	  public String email;
	  public double distance;
	  public String act;
	  
	  public LeaderBoardEntry() {
		  
	  }
	  
	  public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}

