package application.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.model.Controller;
import application.model.MooLogic;

public class MooBootUp {

	
		static Connection connection;
		static Statement stmt;
		static ResultSet rs;
		static UI gw;

		public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
			UI ui = new SimpleWindow("Moo");
			MooLogic logic = new MooLogic();
			Controller controller = new Controller(ui, logic);
			controller.startApplication();
			
		}
		
		
	

}
