package models;

public class Window {
	private boolean windowExists;
	private boolean windowIsOpen;

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
	public boolean getWindowExists() {
		return windowExists;
	}
	public void setWindowExists (boolean exist) {
		this.windowExists = exist;
	}

}
