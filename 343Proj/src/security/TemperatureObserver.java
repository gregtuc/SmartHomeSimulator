package security;

import java.io.IOException;

public interface TemperatureObserver {
    void alarm(String type, String name, int period, String simulator) throws IOException;
}
