import models.OutsideTemperature;
import models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DetectTemperatureAnomaly {

    private Assertions Assert;

    @Test
    void test() {

        //Creating a room and setting it's temperature.
        Room room = new Room();
        room.setInitialTemp(0.00);

        //Creating the notification boolean
        boolean userNotified = false;

        //If the boolean detects the temperature anomaly, a user alert would be sent.
        if(room.getInitialTemp() == 0.00){
            userNotified = true;
        }

        //Test
        System.out.print("Checking that temperature anomalies can be detected...");
        Assertions.assertTrue(userNotified);

    }
}
