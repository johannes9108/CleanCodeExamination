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
			newGameScreen();
			printCurrentPlayer(currentPlayer);
			//comment out or remove next line to play real games!
			printCheatSheet(goal);
			String guess = getGuess();
			//!!!!!!!ADDED DISPLAYPROGRESS FOR SPECIAL BEHAVIOR AT FIRST RUN
			printCurrentProgress(guess);
			int nGuess = 1;
			String bbcc = checkBC(goal, guess);
			
			printCurrentProgress(bbcc);
			while ( ! bbcc.equals("BBBB,")) {
				nGuess++;
				guess = getGuess();
				printCurrentProgress( guess);
				bbcc = checkBC(goal, guess);
				printCurrentProgress(bbcc);
			}
//			int ok = stmt.executeUpdate("INSERT INTO results " + 
//					"(result, player) VALUES (" + nGuess + ", " +	id + ")" );
//			showTopTen();
			answer = JOptionPane.showConfirmDialog(null, "Correct, it took " + nGuess
					+ " guesses\nContinue?");
		
		}
		uiExit();		
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
	
	public static String makeGoal(){
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
	
	public static String checkBC(String goal, String guess) {
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
	
	static class PlayerAverage {
		String name;
		double average;
		public PlayerAverage(String name, double average) {
			this.name = name;
			this.average = average;
		}	
	}
	
//	static void showTopTen() throws SQLException {
//		ArrayList<PlayerAverage> topList = new ArrayList<>();
//		Statement stmt2 = connection.createStatement();
//		ResultSet rs2;
//		rs = stmt.executeQuery("select * from players");
//		while(rs.next()) {
//			int id = rs.getInt("id");
//			String name = rs.getString("name");
//			rs2 = stmt2.executeQuery("select * from results where player = "+ id );
//			int nGames = 0;
//			int totalGuesses = 0;
//			while (rs2.next()) {
//				nGames++;
//				totalGuesses += rs2.getInt("result");
//			}
//			if (nGames > 0) {
//				topList.add(new PlayerAverage(name, (double)totalGuesses/nGames));
//			}
//			
//		}
//		ui.addString("Top Ten List\n    Player     Average\n");
//		int pos = 1;
//		topList.sort((p1,p2)->(Double.compare(p1.average, p2.average)));
//		for (PlayerAverage p : topList) {
//			ui.addString(String.format("%3d %-10s%5.2f%n", pos, p.name, p.average));
//			if (pos++ == 10) break;
//		}
//
//	}

}
