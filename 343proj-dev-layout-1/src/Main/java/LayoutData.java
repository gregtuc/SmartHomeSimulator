package Main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jgrapht.*;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class LayoutData {

    // Creating the data structure to hold the graph data
    Graph<Room, DefaultEdge> layoutGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public static void main(String[] args) throws Exception{
        Graph<Room, DefaultEdge> layoutGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        // Creating the grid of empty spaces
        // Room r1 = new Room("Not built", 1, null, null, true, 0, 23.5);


        // Adding vertices to the graph
        //layoutGraph.addVertex(r1);


        // Room roomArray[] = new Room[];

        // Parameters to be recorded from input:
        // List of rooms
        ArrayList<Integer> existingRooms = new ArrayList<Integer>();

        // pass the path to the file as a parameter
        File file =
                new File("layout.txt");
        Scanner sc = new Scanner(file);
        String currentString = null;
        String[] words = new String[0];
        // Scan through the layout text file one line at a time
        while (sc.hasNextLine()) {
            // Save the current string
            currentString = sc.nextLine();
            // Split the current string into an array of words, delimited by whitespaces
            words = currentString.split("\\s+");
            // Save the list of rooms
            if (words[0].equals("0:")) {
                for (int i = 1; i <= words.length; i++) {
                    existingRooms.add(Integer.parseInt(words[i]));
                }
                // Create edges between ALL existing rooms
                // Likely to cause issues later, since not all rooms are adjacent to each other
                for (int j = 0; j < existingRooms.size(); j++) {
                    Room roomA = null;
                    for (int k = 0; k < existingRooms.size(); k++) {
                        if (j == k)
                            continue;

                        // layoutGraph.addEdge(r1, r2, new RoomEdge(false, true));
                    }
                }
            }
            //
            if (words[0].equals("{")) {

            }
            switch (words[0]) {
                case "1:":{

                }
                case "2:":{

                }
                case "3:":{

                }
                case "4:":{

                }
                case "5:":{

                }
            }

        }
    }
}
