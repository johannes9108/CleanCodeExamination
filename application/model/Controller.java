package application.model;

import javax.swing.JOptionPane;

import application.integration.DAO;
import application.model.bo.Player;
import application.ui.UI;

public class Controller {

	private UI ui;
	private AbstractGame game;
	private DAO dao;
	
	private Player currentPlayer;
	
	
	public Controller(UI ui, AbstractGame game,DAO dao) {
		this.ui = ui;
		this.game = game;
		this.dao = dao;
	}

	public void startApplication() {
		
			int keepPlaying = ui.optionPaneAnswer();
			login();
			while (keepPlaying == ui.optionPaneAnswer()) {
				setUp();
				gameLoop();
				showTopTen();
				keepPlaying = ui.keepPlayingDialog("Correct, it took " + game.getGuesses()
						+ " guesses\nContinue?");
			}
			uiExit();		
	}

	private void showTopTen() {
		publishResult(game.getGuesses(),currentPlayer.getId());
		printMessage(getTopTen());		
	}

	private void gameLoop() {
		String progress = "";
		do {
			String guess = getGuess();
			if(game.validate(guess)) {
				int guesses = game.getGuesses();
				game.setGuesses(guesses+1);
				printMessage(guess);
			}
			else {
				printMessage("Invalid format");
				continue;
			}
			progress = game.generateProgress(guess);
			printMessage(progress);
		}while (!progress.equals( game.getWinCondition()));
		
	}
	
	private void setUp() {
		game.generateAnswer();
		newGameScreen();
		printMessage(currentPlayer.getName());
		//comment out or remove next line to play real games!
		printMessage("For practice, number is: " + game.getGoal());
	}
	

	private void login() {
		String playerName = askForUserName();
		currentPlayer = getPlayerByName(playerName);
		
	}

	private String askForUserName() {
		ui.addString("Enter your user name:\n");
		String name = ui.getString();
		return name;
	}

	private void newGameScreen() {
		ui.clear();
		ui.addString("New game:\n");		
	}

	private String getGuess() {
		String guess = ui.getString();
//		ui.addString(guess +"\n");
		return guess;
	}

	private void printMessage(String msg) {
		ui.addString(msg + "\n");		
	}

	private void uiExit() {
		ui.exit();		
	}

	public Player getPlayerByName(String currentPlayer) {
		return dao.getPlayerByName(currentPlayer).get();
	}

	private void publishResult(int guesses, int id) {
		dao.publishNewResult(guesses,id);
	}

	private String getTopTen() {
		return dao.getTop10();
	}
	
}
