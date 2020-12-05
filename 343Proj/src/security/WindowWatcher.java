package security;

import java.io.IOException;
import java.util.ArrayList;

// This is to see if a window opens/closes, for the sake of temperature regulation.
public class WindowWatcher {
    private ArrayList<WindowObserver> listeners = new ArrayList<>();

    public void subscribe(WindowObserver observer){
        listeners.add(observer);
    }
    public void triggerAlarm(String roomName, String simulator) throws IOException {
        for (WindowObserver listener : listeners) {
            listener.alarm(roomName, simulator);
        }
    }
}
