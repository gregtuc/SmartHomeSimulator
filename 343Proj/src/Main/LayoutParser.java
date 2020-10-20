package Main;


import java.io.*;
import java.util.*;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import models.Room;

public class LayoutParser {

    public static ArrayList<ArrayList<Room>> grid = new ArrayList<ArrayList<Room>>();

    /*
    THIS IS HOW THE LAYOUT GRID SYSTEM WORKS:
    -----------------------------------------
        1 2 3 4
    1  | | | | |
    2  | |1|3| |
    3  |6|2| | |
    4  | |5|4| |

    The counters "i" and "j" in the code refer to the positions on the grid.
    We're using an Arraylist of Arraylists to create a 2D matrix.
    Thus, the grid is more or less read like this: grid[column][row], or grid[j][i].

    The current line in the layout text document is the current row (counted by i).
    This means that each split element in the "words" array is the column position (counted by j).
     */

    public static void parseLayout(TextArea[][] panes) throws Exception {
        for (int row = 0; row < 4; row++) {
            grid.add(new ArrayList<Room>());
            for (int col = 0; col < 4; col++) {
                grid.get(row).add(new Room(0, -1, -1));
            }
            System.out.println("");
        }

        File file =
                new File("src/layout.txt");
        Scanner sc = new Scanner(file);
        String currentLine = null;
        String[] words = new String[0];

        //*** PARSE THE GRID ***//
        int i = 0, j = 0; // Counters for the grid iteration.
        // Iterate through each row of the grid, collect all marked room positions.
        do {
            currentLine = sc.nextLine();
            if (currentLine.equals("{")) {
                continue;
            }
            words = currentLine.split("\\|"); // Gather all elements of the row (inc. whitespaces) as strings.
            // Iterate through the row.
            for (int c = 1; c < words.length; c++) {
                // If the slot in the grid is empty...
                if (words[c].equals(" ")) {
                    // ... count the column as "done"...
                    j++;
                    // ... then move to the next slot.
                    continue;
                }
                // If the slot has something in it (guaranteed to be a number)...
                else {
                    // ... parse the number into an int...
                    int number = Integer.parseInt(words[c]);
                    // ... create a Room and save it to the grid...
                    grid.get(i).set(j, new Room(number, j, i));
                    // ... then move to the next slot.
                    j++;
                }
            }
            j = 0; // Reset the column counter for the next row.
            i++;
        } while (sc.hasNextLine() && !currentLine.equals("}"));

        //*** PARSE THE ROOM NAMES AND ATTRIBUTES ***//
        do {
            currentLine = sc.nextLine();
            if (currentLine.equals("{")) {
                continue;
            }
            if (currentLine.equals("}")) {
                break;
            }
            words = currentLine.split(" : "); // Split the line into the room number and the room name.
            int number = Integer.parseInt(words[0]);
            // Search for the room number among the grid, then assign the new name.
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (grid.get(row).get(col).graphNumber == number) {
                        grid.get(row).get(col).roomName = words[1];
                        switch (words[2]) {
                            case "None": {
                                break;
                            }
                            case "Window": {
                                grid.get(row).get(col).window = true;
                                break;
                            }
                            case "Door": {
                                grid.get(row).get(col).door = true;
                                break;
                            }
                            case "Both": {
                                grid.get(row).get(col).door = true;
                                grid.get(row).get(col).window = true;
                                break;
                            }

                        }
                    }
                }
            }
        } while (sc.hasNextLine() && !currentLine.equals("}"));

        // Print out the grid to the console for verification.
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print(grid.get(row).get(col));
            }
            System.out.println("");
        }
        System.out.println();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;

                //Dynamically update the grid with the room names.
                panes[col][row].setText("Room #"+room.graphNumber+" is the "+room.roomName
                        +". Door: "+room.door+" | Window: "+room.window+"\n");

                System.out.print(
                        "Room #"+room.graphNumber+" is the "+room.roomName
                                +". Door: "+room.door+" | Window: "+room.window+"\n"
                );
            }
        }
        System.out.println();
    }

    public static void insertProfile(String location, String oldLocation, TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    Pane test = (Pane)panes[col][row].getParent();
                    test.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                if(oldLocation.equals(room.roomName)){
                    Pane test = (Pane)panes[col][row].getParent();
                    test.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    public static void insertPerson(String location, TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    Pane test = (Pane)panes[col][row].getParent();
                    test.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                }
            }
        }
    }

    public ArrayList<ArrayList<Room>> getGrid(){
        return grid;
    }
}