package utility;

public class LayoutFileException extends Exception{
    public LayoutFileException(){
        super("There was a problem finding the layout file. Try checking the path.");
    }
}
