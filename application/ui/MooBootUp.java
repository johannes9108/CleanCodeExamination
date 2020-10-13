package application.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.integration.DAO;
import application.integration.MooDAOJDBCImpl;
import application.model.Controller;
import application.model.MooLogic;

public class MooBootUp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
			UI ui = new SimpleWindow("Moo");
			MooLogic logic = new MooLogic();
			DAO dao = new MooDAOJDBCImpl();
			Controller controller = new Controller(ui, logic, dao);
			controller.startApplication();
			
		}
		
		
	

}
