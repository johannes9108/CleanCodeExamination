package application.ui;

import java.sql.SQLException;

import application.integration.DAO;
import application.integration.MooDAOJDBCImpl;
import application.model.AbstractGame;
import application.model.Controller;
import application.model.MooGame;

public class MooBootUp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
			UI ui = new SimpleWindow("Moo");
			AbstractGame game = new MooGame();
			DAO dao = new MooDAOJDBCImpl();
			Controller controller = new Controller(ui, game, dao);
			controller.startApplication();
			
		}
		
		
	

}
