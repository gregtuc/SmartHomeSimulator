package security;

import java.io.IOException;

public interface WindowObserver {
    void alarm(String roomName, String simulator) throws IOException;
}
