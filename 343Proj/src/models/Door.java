package models;

public class Door {
    private boolean doorExists;
    private boolean doorIsOpen;

    /**
     * Constructor
     * @param exist
     */
    public Door(boolean exist) {
        this.doorExists = exist;
        doorIsOpen = false;
    }

    public boolean getDoorIsOpen() {
        return doorIsOpen;
    }

    public void setDoorIsOpen(boolean isOpen) {
        this.doorIsOpen = isOpen;
    }

    public boolean getDoorExists() {
        return doorExists;
    }

    public void setDoorExists(boolean exist) {
        this.doorExists = exist;
    }
}
