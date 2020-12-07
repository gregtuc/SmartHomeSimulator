import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.ZoneManager;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateZone {

    private Assertions Assert;

    @Test
    void test() throws IOException {
        ZoneManager.createZone("newZone");
        System.out.print("Testing if Zone can be created...");
        assertEquals("newZone", Objects.requireNonNull(ZoneManager.getZone("newZone")).getZoneName());
    }
}
