package application.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.model.bo.Player;
import application.ui.UI;

public class MooDAOJDBCImpl implements DAO {
	

	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	static UI gw;

	
//	private static final String SELECTPLAYERBYNAME = 
	
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Moo","root","root");			
			stmt = connection.createStatement();	
		}
		catch(Exception e) {
			System.err.println("Problems with establishing JDBC connection");
			
		}
	}
	
	@Override
	public Optional<Player> getPlayerById(int id) {
		return Optional.empty();
	}

	@Override
	public Optional<Player> getPlayerByName(String name) {
		
		try {
			rs = stmt.executeQuery("select * from players where name = '" + name + "'");
			
			Optional<Player> player;
			
			if(rs.next()) {
				player = fetchExistingPlayer(rs.getInt("id"),name);
			}
			else {
				player = createNewPlayer(name);
			}
			
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private Optional<Player> fetchExistingPlayer(int id, String name) throws SQLException {
		ArrayList<Integer> highscores = new ArrayList<Integer>();
		rs= stmt.executeQuery("select result from results where player = '"+id+"'");
		while(rs.next()) {
			int result = rs.getInt(1);
			highscores.add(result);
		}
		
		return Optional.of(new Player(id,name,highscores));
		
		
	}

	@Override
	public List<Player> getTop10() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Player> createNewPlayer(String name){
		name = name.toLowerCase();
		try {
			int res = stmt.executeUpdate("INSERT INTO `moo`.`players` ( `name`) VALUES ('"+name + "')");
			return getPlayerByName(name);
			
		}catch(SQLException e) {
			return Optional.empty();
		}
	
	}

}
