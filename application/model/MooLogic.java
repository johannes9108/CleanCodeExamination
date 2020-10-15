package application.model;

import javax.swing.JOptionPane;

import application.model.bo.Player;

public class MooLogic {

	private Controller controller;
	
	protected void setController(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		int answer = JOptionPane.YES_OPTION;

		// login
		String currentPlayer = askForUsername();
		
		Player player = controller.getPlayerByName(currentPlayer);
		System.out.println(player);

		
		while (answer == JOptionPane.YES_OPTION) {
			String goal = makeGoal();
			String bbcc = "";
			newGameScreen();
			printCurrentPlayer(currentPlayer);
			//comment out or remove next line to play real games!
			printCheatSheet(goal);
			String guess = getGuess();
			//!!!!!!!ADDED DISPLAYPROGRESS FOR SPECIAL BEHAVIOR AT FIRST RUN
			
			printCurrentProgress(guess);
			int nGuess = 1;
			
			// Skipping if guess is too short
			if(guessIsToShort(guess)) {
				nGuess--;
				
			}else {
				bbcc = checkBC(goal, guess);	
				printCurrentProgress(bbcc);
			}
			
			
			while ( ! bbcc.equals("BBBB,")) {
				nGuess++;
				guess = getGuess();
				printCurrentProgress( guess);
				// Skipping if guess is too short
				if(guessIsToShort(guess)) {
					nGuess--;
					continue;
				}
				bbcc = checkBC(goal, guess);
				printCurrentProgress(bbcc);
			}
			publishResult(nGuess,player.getId());
			printTopTen();
			answer = JOptionPane.showConfirmDialog(null, "Correct, it took " + nGuess
					+ " guesses\nContinue?");
		
		}
		uiExit();		
	}

	private boolean guessIsToShort(String guess) {
		if(guess.length()<4) {
			printCurrentProgress("Use format XXXX!");
			return true;
		}
		return false;
	}

	private void printTopTen() {
		String topTenMsg =  controller.getTopTen();
		controller.displayProgress(topTenMsg);
	}

	private void publishResult(int guesses,int id) {
		controller.publishResult(guesses,id);
	}

	private void printCurrentPlayer(String currentPlayer) {
		printCurrentProgress("Player: " + currentPlayer);
	}

	private void uiExit() {
		controller.uiExit();
	}

	private void printCheatSheet( String goal) {
		controller.printCheatSheet(goal);
	}

	private void printCurrentProgress( String msg) {
		controller.displayProgress(msg);
	}

	private String getGuess() {
		return controller.getGuess();
	}

	private void newGameScreen() {
		controller.newGameScreen();
	}

	private String askForUsername() {
		return controller.askForUserName();
	}
	
	private static String makeGoal(){
		String goal = "";
		for (int i = 0; i < 4; i++){
			int random = (int) (Math.random() * 10);
			String randomDigit = "" + random;
			while (goal.contains(randomDigit)){
				random = (int) (Math.random() * 10);
				randomDigit = "" + random;
			}
			goal = goal + randomDigit;
		}
		return goal;
	}
	
	private static String checkBC(String goal, String guess) {
		int cows = 0, bulls = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++ ) {
				if (goal.charAt(i) == guess.charAt(j)){
					if (i == j) {
						bulls++;
					} else {
						cows++;
					}
				}
			}
		}
		String result = "";
		for (int i = 0; i < bulls; i++){
			result = result + "B";
		}
		result = result + ",";
		for (int i = 0; i < cows; i++){
			result = result + "C";
		}
		return result;
	
	}
	

}
