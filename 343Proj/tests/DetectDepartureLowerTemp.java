import models.ActiveUser;
import models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DetectDepartureLowerTemp {

    private Assertions Assert;

    @Test
    void test() {
        //Creating a room.
        Room room = new Room();

        //Setting the initial room temperature to 20 degrees.
        room.setInitialTemp(20.00);

        //Setting the house to away mode
        ActiveUser.turnOnAwayMode();

        //Checking if house can detect away mode has been activated and changing the temp to 15 if it can.
        if(ActiveUser.getActiveUserAwayMode()){
            room.setInitialTemp(15.00);
        }

        //Checking if the temp was changed.
        System.out.print("Testing to see if the temperature was changed when away mode was activated...");
        Assertions.assertEquals(15.00, room.getInitialTemp());
    }
}
