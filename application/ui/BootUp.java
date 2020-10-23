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
			Controller controller = GameSelector.gameSelector(ui);
			controller.startApplication();
		}
		
		
	

}
