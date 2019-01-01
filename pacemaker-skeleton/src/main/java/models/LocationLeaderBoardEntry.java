package models;

public class LocationLeaderBoardEntry {

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
