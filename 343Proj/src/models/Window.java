package models;

public class Window {
    private boolean windowExists;
    private boolean windowIsOpen;
    private boolean isBlocked;

    public Window(boolean exist) {
        this.windowExists = exist;
        windowIsOpen = false;
    }

    public boolean getWindowIsOpen() {
        return windowIsOpen;
    }

    public void setWindowIsOpen(boolean isOpen) {
        this.windowIsOpen = isOpen;
    }

    public Boolean canOpenWindow(){
        return !isBlocked;
    }

    public boolean getWindowExists() {
        return windowExists;
    }

    public void setWindowExists(boolean exist) {
        this.windowExists = exist;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
