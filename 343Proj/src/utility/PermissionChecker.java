package utility;

import controllers.ProfileController;
import models.ActiveUser;

public class PermissionChecker {
    private static final PermissionChecker instance = null;

    public static Boolean checkCorePerms(String requestedRoom){
        if(ProfileController.userHasCorePermissions(ActiveUser.getActiveUsername())){
            if(ActiveUser.getActiveUserType().equals("Child") || ActiveUser.getActiveUserType().equals("Guest")){
                return ActiveUser.getActiveUserLocation().equals(requestedRoom);
            }
            return true;
        }
        return false;
    }

    public static Boolean checkSecurityPerms(){
        return ProfileController.userHasSecurityPermissions();
    }
    public static Boolean checkActiveUserIsLoggedIn() {
    	String userName = ActiveUser.getActiveUsername();
        return !userName.equals("");
    }
}
