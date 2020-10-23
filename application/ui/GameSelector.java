package application.ui;

import application.integration.AbstractGameDAO;
import application.integration.MooDAOJDBCImpl;
import application.integration.NumberGameDAOJDBCImpl;
import application.model.AbstractGame;
import application.model.Controller;
import application.model.MooGame;
import application.model.NumberGuessGame;

public class GameSelector {
	
	static Controller gameSelector(UI ui) {
		
		String gameChoice = "";
		do {
			ui.addString("Which game you want to play:\nmoo/number\n");
			gameChoice = ui.getString();
			gameChoice.toLowerCase();
			
		}while(!(gameChoice.equals("moo") || gameChoice.equals("number")));
		ui.clear();
		// Fixa  choicesatsen
		AbstractGame game = null;
		AbstractGameDAO dao = null;
		switch(gameChoice) {
		case "moo":
			game = new MooGame();
			dao = new MooDAOJDBCImpl();
			break;
		case "number":
			game = new NumberGuessGame();
			dao = new  NumberGameDAOJDBCImpl();
		default:
			break;
		}
		return new Controller(ui, game, dao);
		
	}
	
	
	
	

}
