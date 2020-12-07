import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.ZoneManager;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeZoneTemperature {

    private Assertions Assert;

    @Test
    void test() throws IOException {
        ZoneManager.createZone("newZone");
        ZoneManager.setZoneTemperatures("newZone", 25, 30, 35);

        ArrayList<Double> temperatures = new ArrayList<Double>();
        temperatures.add((double) 25);
        temperatures.add((double) 30);
        temperatures.add((double) 35);

        System.out.println("Testing if zone temperatures can be changed and configured...");
        assertEquals(temperatures, ZoneManager.zoneTemperatures("newZone"));
    }
}
