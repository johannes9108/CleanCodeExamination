package application.model;

import java.util.Optional;

import application.integration.AbstractGameDAO;
import application.model.bo.Player;
import application.ui.UI;

public class Controller {

	private UI ui;
	private AbstractGame game;
	private AbstractGameDAO dao;
	
	private Player currentPlayer;
	
	
	public Controller(UI ui, AbstractGame game,AbstractGameDAO dao) {
		this.ui = ui;
		this.game = game;
		this.dao = dao;
	}

	public void startApplication() {
		
			int keepPlaying = ui.optionPaneAnswer();
			
			while (keepPlaying == ui.optionPaneAnswer()) {
				setUp();
				gameLoop();
				handleResult();
				keepPlaying = ui.keepPlayingDialog("Correct, it took " + game.getGuesses()
						+ " guesses\nContinue?");
			}
			uiExit();		
	}

	private void handleResult() {
		saveResult(game.getGuesses(),currentPlayer.getId());
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
		while(!login().isPresent());
		game.setGuesses(0);
		game.generateAnswer();
		newGameScreen();
		printMessage(currentPlayer.getName());
		//comment out or remove next line to play real games!
		printMessage("For practice, number is: " + game.getGoal());
	}
	

	private Optional<Player> login() {
		String playerName = askForUserName();
		Optional<Player> player = getPlayerByName(playerName);
		if(player.isPresent())
			currentPlayer = player.get();
		else
			printMessage("Couldn't find player");
		return player;
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

	public Optional<Player> getPlayerByName(String currentPlayer) {
		return dao.getPlayerByName(currentPlayer);
	}

	private void saveResult(int guesses, int id) {
		dao.insertNewResult(guesses,id);
	}

	private String getTopTen() {
		return dao.getTop10();
	}
	
}
