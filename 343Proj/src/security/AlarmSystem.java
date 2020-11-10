package security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AlarmSystem {
	private static int waitDelay = 1;
    private ArrayList<Observer> listeners = new ArrayList<>();

    public void subscribe(Observer observer){
        listeners.add(observer);
    }
    public void triggerAlarm() throws IOException {
    	try {
			TimeUnit.SECONDS.sleep(this.waitDelay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (Observer listener : listeners) {
            listener.alarm();
        }
    }
	/**
	 * @return the waitDelay
	 */
	public static int getWaitDelay() {
		return waitDelay;
	}
	/**
	 * @return the waitDelay as a string
	 */
	public static String getWaitDelayString() {
		return String.valueOf(waitDelay);
	}
	/**
	 * @param waitDelay the waitDelay to set
	 */
	public static void setWaitDelay(int waitDelay) {
		AlarmSystem.waitDelay = waitDelay;
	}
    
}
