package utility;

import controllers.HomeController;
import security.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CommandLogger implements Observer {
    private static volatile CommandLogger instance = null;

    private CommandLogger() {
    }

    //Only a singular instantiation of this class will be allowed.
    //This method will return that instance.
    public static CommandLogger getInstance() {
        if (instance == null) {
            synchronized (CommandLogger.class) {
                if (instance == null) {
                    instance = new CommandLogger();
                }
            }
        }
        return instance;
    }

    public static void initialize() {
        HomeController.alarmSystem.subscribe(CommandLogger.getInstance());
    }

    public static void logCommand(String sender, String message) throws IOException {
        //Creating log message.
        String output = "[" + sender + "] " + message;
        //Write to output file.
        try (FileWriter fw = new FileWriter("343Proj/src/commandlog.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Write to visual console.
        UniversalElements.getOutputConsoleText().setText(UniversalElements.getOutputConsoleText().getText() + "\n" + output);
    }

    @Override
    public void alarm() throws IOException {
        CommandLogger.logCommand("SHP", "Motion detectors have been triggered! Alarm activated. Authorities and Home-Owner have been contacted.");
    }
}
