package models;

public class Window {
	private boolean windowExists;
	private boolean windowIsOpen;
	
	/**
	 * Constructor 
	 * @param exist
	 */
	public Window(boolean exist) {
		this.windowExists = exist;
		windowIsOpen = false;
	}
	/**
	 * getter for windowIsOpen
	 * @return
	 */
	public boolean getWindowIsOpen() {
		return windowIsOpen;
	}
	/**
	 * setter for windowIsOpen
	 * @param isOpen
	 */
	public void setWindowIsOpen(boolean isOpen) {
		this.windowIsOpen = isOpen;
	}
	/**
	 * getter for windowExists
	 * @return
	 */
	public boolean getWindowExists() {
		return windowExists;
	}
	/**
	 * setter for windowExists
	 * @param exista
	 */
	public void setWindowExists (boolean exist) {
		this.windowExists = exist;
	}

}
