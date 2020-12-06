package utility;


import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Clock;

public class UniversalElements {
    private static volatile UniversalElements instance = null;
    //Label Elements
    private static Label timeLabel = new Label();
    private static Label userLabel = new Label();
    private static Label outsideTemperatureLabel = new Label();
    private static Label locationLabel = new Label();
    private static Label awayModeLabel = new Label();
    private static Label simulationSpeedLabel = new Label();
    private static Clock clock = new Clock();

    //ListView Elements
    private static ListView<String> zoneTemperatureRoomList = new ListView<String>();

    //TextArea Elements
    private static TextArea outputConsoleText = new TextArea();
    private static TextArea[][] panes = new TextArea[4][4];

    //Creating the singleton pattern.
    private UniversalElements() {
    }

    public static UniversalElements getInstance() {
        if (instance == null) {
            synchronized (UniversalElements.class) {
                if (instance == null) {
                    instance = new UniversalElements();
                }
            }
        }
        return instance;
    }

    public static Label getTimeLabel() {
        return timeLabel;
    }

    public static void setTimeLabel(Label timeLabel) {
        UniversalElements.timeLabel = timeLabel;
    }

    public static Label getUserLabel() {
        return userLabel;
    }

    public static void setUserLabel(Label userLabel) {
        UniversalElements.userLabel = userLabel;
    }

    public static Label getOutsideTemperatureLabel() {
        return outsideTemperatureLabel;
    }

    public static void setOutsideTemperatureLabel(Label outsideTemperatureLabel) {
        UniversalElements.outsideTemperatureLabel = outsideTemperatureLabel;
    }

    public static Label getLocationLabel() {
        return locationLabel;
    }

    public static void setLocationLabel(Label locationLabel) {
        UniversalElements.locationLabel = locationLabel;
    }

    public static Label getAwayModeLabel() {
        return awayModeLabel;
    }

    public static void setAwayModeLabel(Label awayModeLabel) {
        UniversalElements.awayModeLabel = awayModeLabel;
    }

    public static Label getSimulationSpeedLabel() {
        return simulationSpeedLabel;
    }

    public static void setSimulationSpeedLabel(Label simulationSpeedLabel) {
        UniversalElements.simulationSpeedLabel = simulationSpeedLabel;
    }

    public static String getSelectedZone() {
        return zoneTemperatureRoomList.getSelectionModel().getSelectedItem();
    }

    public static void setZoneTemperatureRoomList(ListView<String> zoneTemperatureRoomList) {
        UniversalElements.zoneTemperatureRoomList = zoneTemperatureRoomList;
    }

    public static TextArea getOutputConsoleText() {
        return outputConsoleText;
    }

    public static void setOutputConsoleText(TextArea outputConsoleText) {
        UniversalElements.outputConsoleText = outputConsoleText;
    }

    public static Clock getClock() {
        return clock;
    }


    public static void setClock(Clock clock) {
        UniversalElements.clock = clock;
    }

    public static String getCurrentTime() {
        return String.format("%02d", clock.getHour()) + " : " + String.format("%02d", clock.getMinute()) + " : " + String.format("%02d", clock.getSecond());
    }

    public static TextArea[][] getPanes() {
        return panes;
    }

    public static void setPanes(TextArea[][] panes) {
        UniversalElements.panes = panes;
    }
}
