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


public class ToConsole {

    // Creating the data structure to hold the graph data
    Graph<Room, DefaultEdge> layoutGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);

    public static void main(String[] args) throws Exception{
        Graph<Room, DefaultEdge> layoutGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        // Creating the grid of empty spaces
        Room r1 = new Room("Not built", 1, null, null, true, 0, 23.5);
        Room r2 = new Room("Not built", 2, null, null, true, 0, 23.5);
        Room r3 = new Room("Not built", 3, null, null, true, 0, 23.5);
        Room r4 = new Room("Not built", 4, null, null, true, 0, 23.5);
        Room r5 = new Room("Not built", 5, null, null, true, 0, 23.5);
        Room r6 = new Room("Not built", 6, null, null, true, 0, 23.5);
        Room r7 = new Room("Not built", 7, null, null, true, 0, 23.5);
        Room r8 = new Room("Not built", 8, null, null, true, 0, 23.5);
        Room r9 = new Room("Not built", 9, null, null, true, 0, 23.5);
        Room r10 = new Room("Not built", 10, null, null, true, 0, 23.5);
        Room r11 = new Room("Not built", 11, null, null, true, 0, 23.5);
        Room r12 = new Room("Not built", 12, null, null, true, 0, 23.5);
        Room r13 = new Room("Not built", 13, null, null, true, 0, 23.5);
        Room r14 = new Room("Not built", 14, null, null, true, 0, 23.5);
        Room r15 = new Room("Not built", 15, null, null, true, 0, 23.5);
        Room r16 = new Room("Not built", 16, null, null, true, 0, 23.5);
        Room r17 = new Room("Not built", 17, null, null, true, 0, 23.5);
        Room r18 = new Room("Not built", 18, null, null, true, 0, 23.5);
        Room r19 = new Room("Not built", 19, null, null, true, 0, 23.5);
        Room r20 = new Room("Not built", 20, null, null, true, 0, 23.5);
        Room r21 = new Room("Not built", 21, null, null, true, 0, 23.5);
        Room r22 = new Room("Not built", 22, null, null, true, 0, 23.5);
        Room r23 = new Room("Not built", 23, null, null, true, 0, 23.5);
        Room r24 = new Room("Not built", 24, null, null, true, 0, 23.5);
        Room r25 = new Room("Not built", 25, null, null, true, 0, 23.5);
        Room r26 = new Room("Not built", 26, null, null, true, 0, 23.5);
        Room r27 = new Room("Not built", 27, null, null, true, 0, 23.5);
        Room r28 = new Room("Not built", 28, null, null, true, 0, 23.5);
        Room r29 = new Room("Not built", 29, null, null, true, 0, 23.5);
        Room r30 = new Room("Not built", 30, null, null, true, 0, 23.5);
        Room r31 = new Room("Not built", 31, null, null, true, 0, 23.5);
        Room r32 = new Room("Not built", 32, null, null, true, 0, 23.5);

        // Adding vertices to the graph
        layoutGraph.addVertex(r1);
        layoutGraph.addVertex(r2);
        layoutGraph.addVertex(r3);
        layoutGraph.addVertex(r4);
        layoutGraph.addVertex(r5);
        layoutGraph.addVertex(r6);
        layoutGraph.addVertex(r7);
        layoutGraph.addVertex(r8);
        layoutGraph.addVertex(r9);
        layoutGraph.addVertex(r10);
        layoutGraph.addVertex(r11);
        layoutGraph.addVertex(r12);
        layoutGraph.addVertex(r13);
        layoutGraph.addVertex(r14);
        layoutGraph.addVertex(r15);
        layoutGraph.addVertex(r16);
        layoutGraph.addVertex(r17);
        layoutGraph.addVertex(r18);
        layoutGraph.addVertex(r19);
        layoutGraph.addVertex(r20);
        layoutGraph.addVertex(r21);
        layoutGraph.addVertex(r22);
        layoutGraph.addVertex(r23);
        layoutGraph.addVertex(r24);
        layoutGraph.addVertex(r25);
        layoutGraph.addVertex(r26);
        layoutGraph.addVertex(r27);
        layoutGraph.addVertex(r28);
        layoutGraph.addVertex(r29);
        layoutGraph.addVertex(r30);
        layoutGraph.addVertex(r31);
        layoutGraph.addVertex(r32);

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

                        //layoutGraph.addEdge()
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
