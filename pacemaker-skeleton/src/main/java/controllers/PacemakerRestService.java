package controllers;

import io.javalin.Context;
import models.Activity;
import models.Location;
import models.Message;
import models.User;

import static models.Fixtures.users;

public class PacemakerRestService {

  PacemakerAPI pacemaker = new PacemakerAPI();

  PacemakerRestService() {
    users.forEach(
        user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
  }

  public void listUsers(Context ctx) {
    ctx.json(pacemaker.getUsers());
    System.out.println("list users requested");
  }
  
  public void createUser(Context ctx) {
    User user = ctx.bodyAsClass(User.class);
    User newUser = pacemaker
        .createUser(user.firstname, user.lastname, user.email, user.password);
    ctx.json(newUser);
  }
  
  public void listUser(Context ctx) {
    String id = ctx.pathParam("id");
    ctx.json(pacemaker.getUser(id));
  }
  
  public void getActivities(Context ctx) {
	    String id = ctx.pathParam("id");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      ctx.json(user.activities.values());
	    } else {
	      ctx.status(404);
	    }
	  }

	  public void createActivity(Context ctx) {
	    String id = ctx.pathParam("id");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      Activity activity = ctx.bodyAsClass(Activity.class);
	      Activity newActivity = pacemaker
	          .createActivity(id, activity.type, activity.location, activity.distance);
	      ctx.json(newActivity);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void getActivity(Context ctx) {
	        String id = ctx.pathParam("activityid");  
	    Activity activity = pacemaker.getActivity(id);
	    if (activity != null) {
	      ctx.json(activity);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void getActivityLocations(Context ctx) {
	    String id = ctx.pathParam("activityid");
	    Activity activity = pacemaker.getActivity(id);
	    if (activity != null) {
	      ctx.json(activity.route);
	    } else {
	      ctx.status(404);
	    }
	  }

	  public void addLocation(Context ctx) {
	    String id = ctx.pathParam("activityid");
	    Activity activity = pacemaker.getActivity(id);
	    if (activity != null) {
	      Location location = ctx.bodyAsClass(Location.class);
	      activity.route.add(location);
	      ctx.json(location);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void deleteUser(Context ctx) {
	    String id = ctx.pathParam("id");
	    ctx.json(pacemaker.deleteUser(id));
	  }

	  public void deleteUsers(Context ctx) {
	    pacemaker.deleteUsers();
	    ctx.json(204);
	  }
	  
	  public void deleteActivities(Context ctx) {
	    String id = ctx.pathParam("id");
	    pacemaker.deleteActivities(id);
	    ctx.json(204);
	  }
	  
	  public void follow(Context ctx) {
		String id = ctx.pathParam("id");
		String email = ctx.bodyAsClass(String.class);
	    User user = pacemaker.getUserByEmail(email);
	    if (user != null) {
	      pacemaker.follow(id, email);
	      ctx.json("Now you follow :" +user.firstname);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void listFriends(Context ctx) {
	    String id = ctx.pathParam("id");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      ctx.json(pacemaker.listFriends(id));
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void unfollowFriend(Context ctx) {
		String id = ctx.pathParam("id");
		String email = ctx.bodyAsClass(String.class);
	    User user = pacemaker.getUserByEmail(email);
	    if (user != null) {
	      pacemaker.unfollowFriend(id, email);
	      ctx.json("Now you don't follow :" +user.firstname);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void messageFriend(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
		  Message message = ctx.bodyAsClass(Message.class);
		  pacemaker.messageFriend(id, message);
		  ctx.json("message sent..");
		} else {
		  ctx.status(404);
		}
	  }
	  
	  public void listMessages(Context ctx) {
	    String id = ctx.pathParam("id");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      ctx.json(user.inbox);
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void messageAllFriends(Context ctx) {
		String id = ctx.pathParam("id");
		User user = pacemaker.getUser(id);
		if (user != null) {
		  String message = ctx.bodyAsClass(String.class);
		  pacemaker.messageAllFriends(id, message);
		  ctx.json("message sent to all..");
		} else {
		  ctx.status(404);
		}
	  }
	  
	  public void distanceLeaderBoard(Context ctx) {
	    String id = ctx.pathParam("id");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      ctx.json(pacemaker.distanceLeaderBoard(id));
	    } else {
	      ctx.status(404);
	    }
	  }
	  
	  public void distanceLeaderBoardByType(Context ctx) {
	    String id = ctx.pathParam("id");
	    String type = ctx.pathParam("type");
	    User user = pacemaker.getUser(id);
	    if (user != null) {
	      ctx.json(pacemaker.distanceLeaderBoardByType(id, type));
	    } else {
	      ctx.status(404);
	    }
	  }
}