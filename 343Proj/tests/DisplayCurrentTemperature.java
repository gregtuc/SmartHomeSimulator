import models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayCurrentTemperature {

    private Assertions Assert;

    @Test
    void test() {
        Room room = new Room();
        room.setInitialTemp(25.00);
        System.out.print("Checking that room temperatures can be displayed when requested...");
        assertEquals(25.00, room.getInitialTemp());
    }
}
