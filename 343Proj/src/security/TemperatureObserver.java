package security;

import java.io.IOException;

public interface TemperatureObserver {
    void alarm(String status, String roomName, String simulator) throws IOException;
}
