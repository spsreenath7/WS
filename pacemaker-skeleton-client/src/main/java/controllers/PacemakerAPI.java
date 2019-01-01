package controllers;

import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Activity;
import models.LeaderBoardEntry;
import models.Location;
import models.LocationLeaderBoardEntry;
import models.Message;
import models.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface PacemakerInterface
{
  @GET("/users")
  Call<List<User>> getUsers();
  
  @POST("/users")
  Call<User> registerUser(@Body User User);
  
  @GET("/users/{id}/activities")
  Call<List<Activity>> getActivities(@Path("id") String id);

  @POST("/users/{id}/activities")
  Call<Activity> addActivity(@Path("id") String id, @Body Activity activity);
  
  @GET("/users/{id}/activities/{activityId}")
  Call<Activity> getActivity(@Path("id") String id, @Path("activityId") String activityId);  
  
  @POST("/users/{id}/activities/{activityId}/locations")
  Call<Location> addLocation(@Path("id") String id, @Path("activityId") String activityId, @Body Location location);
  
  @DELETE("/users")
  Call<User> deleteUsers();

  @DELETE("/users/{id}")
  Call<User> deleteUser(@Path("id") String id);

  @GET("/users/{id}")
  Call<User> getUser(@Path("id") String id);
  
  @DELETE("/users/{id}/activities")
  Call<String> deleteActivities(@Path("id") String id);
  
  @GET("/users/{id}/activities/{activityId}/locations")
  Call<List<Location>> getActivityLocations(@Path("id") String id, @Path("activityId") String activityId);
  
  @POST("/users/{id}/friends")
  Call<String> follow(@Path("id") String id, @Body String email);
  
  @GET("/users/{id}/friends")
  Call<List<User>> listFriends(@Path("id") String id);
  
  @GET("/users/{id}/friendacts/{email}")
  Call<List<Activity>> friendActivityReport(@Path("id") String id, @Path("email") String email);
  
  @PUT("/users/{id}/friends")
  Call<String> unfollowFriend(@Path("id") String id, @Body String email);
  
  @PUT("/users/{id}/message")
  Call<String> messageFriend(@Path("id") String id, @Body Message message);
  
  @GET("/users/{id}/message")
  Call<List<Message>> listMessages(@Path("id") String id);
  
  @POST("/users/{id}/message")
  Call<String> messageAllFriends(@Path("id") String id, @Body String message);
  
  @GET("/users/{id}/leaderboard")
  Call<List<LeaderBoardEntry>> distanceLeaderBoard(@Path("id") String id);
  
  @GET("/users/{id}/leaderboard/longitude/{longitude}/latitude/{latitude}")
  Call<List<LocationLeaderBoardEntry>> locationLeaderBoard(@Path("id") String id, @Path("longitude") String longitude, @Path("latitude") String latitude);
  
  @GET("/users/{id}/leaderboard/{type}")
  Call<List<LeaderBoardEntry>> distanceLeaderBoardByType(@Path("id") String id, @Path("type") String type);
}

public class PacemakerAPI {
	
	
	PacemakerInterface pacemakerInterface;

  public PacemakerAPI(String url) {
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson)).build();
    pacemakerInterface = retrofit.create(PacemakerInterface.class);
  }

  public Collection<User> getUsers() {
    Collection<User> users = null;
    try {
      Call<List<User>> call = pacemakerInterface.getUsers();
      Response<List<User>> response = call.execute();
      users = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return users;
  }


  public User createUser(String firstName, String lastName, String email, String password) {
    User returnedUser = null;
    try {
      Call<User> call = pacemakerInterface.registerUser(new User(firstName, lastName, email, password));
      Response<User> response = call.execute();
      returnedUser = response.body();    
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return returnedUser;
  }

  public Activity createActivity(String id, String type, String location, double distance) {
    Activity returnedActivity = null;
    try {
      Call<Activity> call = pacemakerInterface.addActivity(id, new Activity(type, location, distance));
      Response<Activity> response = call.execute();
      returnedActivity = response.body();    
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return returnedActivity;
  }

  public Activity getActivity(String userId, String activityId) {
   Activity activity = null;
    try {
      Call<Activity> call = pacemakerInterface.getActivity(userId, activityId);
      Response<Activity> response = call.execute();
      activity = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return activity;
  }

  public Collection<Activity> getActivities(String id) {
    Collection<Activity> activities = null;
    try {
      Call<List<Activity>> call = pacemakerInterface.getActivities(id);
      Response<List<Activity>> response = call.execute();
      activities = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return activities;
  }

  public List<Activity> listActivities(String userId, String sortBy) {
	  List<Activity> activities = null;
	    try {
	      Call<List<Activity>> call = pacemakerInterface.getActivities(userId);
	      Response<List<Activity>> response = call.execute();
	      activities = response.body();
	      
	      switch (sortBy) {
	      case "type":
	        activities.sort((a1, a2) -> a1.type.compareTo(a2.type));
	        break;
	      case "location":
	        activities.sort((a1, a2) -> a1.location.compareTo(a2.location));
	        break;
	      case "distance":
	        activities.sort((a1, a2) -> Double.compare(a1.distance, a2.distance));
	        break;
	    }
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return activities;
  }

  public void addLocation(String id, String activityId, double latitude, double longitude) {
    try {
      Call<Location> call = pacemakerInterface.addLocation(id, activityId, new Location(latitude, longitude));
      call.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public User getUserByEmail(String email) {
    Collection<User> users = getUsers();
    User foundUser = null;
    for (User user : users) {
      if (user.email.equals(email)) {
        foundUser = user;
      }
    }
    return foundUser;
  }

  public User getUser(String id) {
    User user = null;
    try {
      Call<User> call = pacemakerInterface.getUser(id);
      Response<User> response = call.execute();
      user = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return user;
  }

  public void deleteUsers() {
    try {
      Call<User> call = pacemakerInterface.deleteUsers();
      call.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public User deleteUser(String id) {
    User user = null;
    try {
      Call<User> call = pacemakerInterface.deleteUser(id);
      Response<User> response = call.execute();
      user = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return user;
  }
	  
	public void deleteActivities(String id) {
	    try {
	      Call<String> call = pacemakerInterface.deleteActivities(id);
	      call.execute();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }
	
  public Collection<Location> getActivityLocations(String id, String activityId) {
	  
	  Collection<Location> locations = null;
    try {
      Call<List<Location>> call = pacemakerInterface.getActivityLocations(id, activityId);
      Response<List<Location>> response = call.execute();
      locations = response.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return locations;
  }
	  
  public String follow(String id, String email) {
	  String followedUser = null;
	    try {
	      Call<String> call = pacemakerInterface.follow(id, email);
	      Response<String> response = call.execute();
	      followedUser = response.body();    
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return followedUser;
	  }
  
  public Collection<User> listFriends(String id) {
	    Collection<User> friends = null;
	    try {
	      Call<List<User>> call = pacemakerInterface.listFriends(id);
	      Response<List<User>> response = call.execute();
	      friends = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return friends;
	  }
  
  public Collection<Activity> friendActivityReport(String id, String email) {
	    Collection<Activity> acts = null;
	    try {
	      Call<List<Activity>> call = pacemakerInterface.friendActivityReport(id, email);
	      Response<List<Activity>> response = call.execute();
	      acts = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return acts;
	  }
  
  public String unfollowFriend(String id, String email) {
	  String unfollowedUser = null;
	    try {
	      Call<String> call = pacemakerInterface.unfollowFriend(id, email);
	      Response<String> response = call.execute();
	      unfollowedUser = response.body();    
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return unfollowedUser;
	  }

  public String messageFriend(String id, String sendEmail, String recvEmail, String message) {
	  String status = null;
	    try {
	      Call<String> call = pacemakerInterface.messageFriend(id, new Message(recvEmail, sendEmail, message));
	      Response<String> response = call.execute();
	      status = response.body();    
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return status;
	  }
  
  public Collection<Message> listMessages(String id) {
	    Collection<Message> friends = null;
	    try {
	      Call<List<Message>> call = pacemakerInterface.listMessages(id);
	      Response<List<Message>> response = call.execute();
	      friends = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return friends;
	  }
  
  public String messageAllFriends(String id, String message) {
	  String status = null;
	    try {
	      Call<String> call = pacemakerInterface.messageAllFriends(id, message);
	      Response<String> response = call.execute();
	      status = response.body();    
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return status;
	  }
  
  public Collection<LeaderBoardEntry> distanceLeaderBoard(String id) {
	    Collection<LeaderBoardEntry> board = null;
	    try {
	      Call<List<LeaderBoardEntry>> call = pacemakerInterface.distanceLeaderBoard(id);
	      Response<List<LeaderBoardEntry>> response = call.execute();
	      board = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return board;
	  }
  
  public Collection<LeaderBoardEntry> distanceLeaderBoardByType(String id, String type) {
	    Collection<LeaderBoardEntry> board = null;
	    try {
	      Call<List<LeaderBoardEntry>> call = pacemakerInterface.distanceLeaderBoardByType(id, type);
	      Response<List<LeaderBoardEntry>> response = call.execute();
	      board = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return board;
	  }
  
  public Collection<LocationLeaderBoardEntry> locationLeaderBoard(String id, String longitude, String latitude) {
	    Collection<LocationLeaderBoardEntry> board = null;
	    try {
	      Call<List<LocationLeaderBoardEntry>> call = pacemakerInterface.locationLeaderBoard(id, longitude, latitude);
	      Response<List<LocationLeaderBoardEntry>> response = call.execute();
	      board = response.body();
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return board;
	  }
	
}