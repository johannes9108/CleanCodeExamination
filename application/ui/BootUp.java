package application.ui;

import java.sql.SQLException;

import application.integration.AbstractGameDAO;
import application.integration.DAO;
import application.integration.MooDAOJDBCImpl;
import application.integration.NumberGameDAOJDBCImpl;
import application.model.AbstractGame;
import application.model.Controller;
import application.model.MooGame;
import application.model.NumberGuessGame;

public class BootUp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
			UI ui = new SimpleWindow("Game");
			String gameChoice = "";
			do {
				ui.addString("Which game you want to play:\nmoo/number\n");
				gameChoice = ui.getString();
				gameChoice.toLowerCase();
				
			}while(!(gameChoice.equals("moo") || gameChoice.equals("number")));
			ui.clear();
			AbstractGame game = new NumberGuessGame();
			AbstractGameDAO dao = new NumberGameDAOJDBCImpl();
			Controller controller = new Controller(ui, game, dao);
			controller.startApplication();
			
		}
		
		
	

}
