import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.ZoneManager;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateZone {

    private Assertions Assert;

    @Test
    void test() {
        ZoneManager.createZone("newZone", "Heating");
        assertEquals("newZone", Objects.requireNonNull(ZoneManager.getZone("newZone")).getZoneName());
    }
}
