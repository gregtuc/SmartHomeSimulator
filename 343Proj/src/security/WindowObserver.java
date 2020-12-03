package security;

import java.io.IOException;

public interface WindowObserver {
    void alarm(String status, String roomName, String simulator) throws IOException;
}
