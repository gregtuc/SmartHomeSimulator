package utility;

import models.ActiveUser;

public class PermissionChecker {
    private static volatile PermissionChecker instance = null;

    public static Boolean checkCorePerms(){
        String userType = ActiveUser.getActiveUserType();
        String location = ActiveUser.getActiveUserLocation();
        if(userType.equals("Parent")){
            return true;
        } else if(userType.equals("Child") || userType.equals("Guest")){
            return !location.equals("");
        } else {
            return false;
        }
    }

    public static Boolean checkSecurityPerms(){
        if(ActiveUser.getActiveUserLocation().length()<=0){
            return false;
        }
        return ActiveUser.getActiveUserType().equals("Parent") || ActiveUser.getActiveUserType().equals("Child");
    }
}
