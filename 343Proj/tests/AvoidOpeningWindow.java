import models.Window;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AvoidOpeningWindow {

    private Assertions Assert;

    @Test
    void test() {
        //Creating a window.
        Window window = new Window(true);

        //Setting the window to being blocked.
        window.setBlocked(true);

        //Attempting to open the window
        System.out.println("Testing to see if the window can be opened while blocked...");
        Assertions.assertEquals(false, window.canOpenWindow());

    }
}
