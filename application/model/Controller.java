package application.model;

import application.integration.DAO;
import application.model.bo.Player;
import application.ui.UI;

public class Controller {

	private UI ui;
	// EXTRACT TILL GENERISK LOGIKKOMPONENT
	private MooLogic logic;
	private DAO dao;
	
	public Controller(UI ui, MooLogic logic,DAO dao) {
		this.ui = ui;
		this.logic = logic;
		logic.setController(this);
		this.dao = dao;
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

	public void printCheatSheet(String goal) {
		ui.addString("For practice, number is: " + goal + "\n");		
	}

	public void uiExit() {
		ui.exit();		
	}

	public Player getPlayerByName(String currentPlayer) {
		return dao.getPlayerByName(currentPlayer).get();
	}

	public void publishResult(int guesses, int id) {
		dao.updateResults(guesses,id);
	}

}
