package utility;

import javafx.scene.control.TextArea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CommandLogger {
    private static volatile PermissionChecker instance = null;

    public static void logCommand(String sender, String message, TextArea textarea) throws IOException {
        //Creating log message.
        String output = "["+sender+"] "+message;
        //Write to output file.
        try(FileWriter fw = new FileWriter("343Proj/src/commandlog.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Write to visual console.
        textarea.setText(textarea.getText()+"\n"+output);
    }
}
