package security;

import java.io.IOException;
import java.util.ArrayList;

public class AlarmSystem {
    private ArrayList<Observer> listeners = new ArrayList<>();

    public void subscribe(Observer observer){
        listeners.add(observer);
    }
    public void triggerAlarm() throws IOException {
        for (Observer listener : listeners) {
            listener.alarm();
        }
    }
}
