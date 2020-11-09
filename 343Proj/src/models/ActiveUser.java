package models;

public class ActiveUser {
    private static volatile ActiveUser instance = null;
    private static String profileName = "";
    private static String profileType = "";
    private static String profileLocation = "";
    private static String oldProfileLocation = "";
    private static Boolean awayMode = false;
    private ActiveUser() {}

    //Only a singular instantiation of this class will be allowed.
    //This method will return that instance.
    public static ActiveUser getInstance() {
        if (instance == null) {
            synchronized(ActiveUser.class) {
                if (instance == null) {
                    instance = new ActiveUser();
                }
            }
        }
        return instance;
    }

    public static void setActiveUser(String name, String type){
        ActiveUser.profileName = name;
        ActiveUser.profileType = type;
    }
    public static void setActiveUserLocation(String location){
        oldProfileLocation = profileLocation;
        ActiveUser.profileLocation = location;
    }
    public static void turnOnAwayMode(){
        awayMode = true;
    }
    public static void turnOffAwayMode(){
        awayMode = false;
    }
    public static String getActiveUsername(){
        return profileName;
    }
    public static String getActiveUserType(){return profileType;}
    public static String getActiveUserLocation(){return profileLocation;}
    public static String getOldProfileLocation(){return oldProfileLocation;}
    public static Boolean getActiveUserAwayMode(){return awayMode;}
}
