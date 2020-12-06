package security;

import java.io.IOException;
import java.util.ArrayList;

// This is to see if a window opens/closes, for the sake of temperature regulation.
public class TemperatureWatcher {
    private ArrayList<TemperatureObserver> listeners = new ArrayList<>();

    public void subscribe(TemperatureObserver observer){
        listeners.add(observer);
    }
    public void triggerAlarm(String status, String roomName, String simulator) throws IOException {
        for (TemperatureObserver listener : listeners) {
            listener.alarm(status, roomName, simulator);
        }
    }
}
