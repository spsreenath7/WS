package models;

public class LeaderBoardEntry {



	public String firstname;
	  public String lastname;
	  public String email;
	  public double distance;
	  public String act;
	  
	  public LeaderBoardEntry(User user, String acttype) {
		  firstname =user.firstname;
		  lastname =user.lastname;
		  email =user.email;
		  distance = user.activities.values()
				  					.stream().filter(act -> act.type.equalsIgnoreCase(acttype))
				  					.map(act -> act.distance)
				  					.reduce(0.0, (i,j) -> i+j);
		  act = acttype;
		  
	  }
	  
	  public LeaderBoardEntry(User user) {
		  firstname =user.firstname;
		  lastname =user.lastname;
		  email =user.email;
		  distance = user.activities.values()
				  					.stream()
				  					.map(act -> act.distance)
				  					.reduce(0.0, (i,j) -> i+j);
		  act = "All";
		  
	  }
	  
	  
	  

	
}

