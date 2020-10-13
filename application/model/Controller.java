package application.model;

import application.ui.UI;

public class Controller {

	private UI ui;
	// EXTRACT TILL GENERISK LOGIKKOMPONENT
	private MooLogic logic;
	
	public Controller(UI ui, MooLogic logic) {
		this.ui = ui;
		this.logic = logic;
		logic.setController(this);
	}

	public void startApplication() {
		logic.run();
		
	}

	public String askForUserName() {
		ui.addString("Enter your user name:\n");
		String name = ui.getString();
		return name;
	}

	public void newGameScreen() {
		ui.clear();
		ui.addString("New game:\n");		
	}

	public String getGuess() {
		String guess = ui.getString();
//		ui.addString(guess +"\n");
		return guess;
	}

	public void displayProgress(String msg) {
		ui.addString(msg + "\n");		
	}

	public void cheatSheet(String goal) {
		ui.addString("For practice, number is: " + goal + "\n");		
	}

	public void uiExit() {
		ui.exit();		
	}

}
