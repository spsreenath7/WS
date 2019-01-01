package models;

public class LocationLeaderBoardEntry {

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

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String firstname;
	public String lastname;
	public String email;
	public String act;
	public String place;
	
	public LocationLeaderBoardEntry(User user, Activity activity)
	{
		firstname = user.firstname;
		lastname = user.lastname;
		email = user.email;
		act = activity.type;
		place = activity.location;
		
	}

}
