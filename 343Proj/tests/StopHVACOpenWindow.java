import models.OutsideTemperature;
import models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopHVACOpenWindow {

    private Assertions Assert;

    @Test
    void test() {

        //Creating a room and setting it's temperature.
        Room room = new Room();
        room.setInitialTemp(25.00);

        //Setting the exterior temperature.
        OutsideTemperature exteriorTemp = new OutsideTemperature();
        exteriorTemp.setTemperature(20);

        //Test
        System.out.print("Checking that HVAC is turned off & windows are open if colder outside than inside (to be completed)...");
        //assertEquals(25.00, room.getInitialTemp());
    }
}
