package application.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.model.PlayerAverage;
import application.model.bo.Player;

public abstract class AbstractGameDAO implements DAO{

	static Connection connection;
	static PreparedStatement stmt;
	static ResultSet rs;
	
	
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Moo","root","root");			
				
		}
		catch(Exception e) {
			System.err.println("Problems with establishing JDBC connection");
			throw new RuntimeException("Problems with JDBC connection");
		}
	}
	
	public Optional<Player> getPlayerByName(String name){
		try {
			stmt = connection.prepareStatement("select * from players where name = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			Optional<Player> player;
			
			if(rs.next()) {
				player =  Optional.of(new Player(rs.getInt(1),name));
			}
			else {
				player = Optional.empty();
			}
			
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
//	private Optional<Player> fetchExistingPlayer(int id, String name) throws SQLException {
//		ArrayList<Integer> highscores = new ArrayList<Integer>();
//		rs= stmt.executeQuery("select result from results where player = '"+id+"'");
//		while(rs.next()) {
//			int result = rs.getInt(1);
//			highscores.add(result);
//		}
//		
//		return Optional.of(new Player(id,name));
//		
//		
//	}
	

//	public Optional<Player> createNewPlayer(String name){
//		name = name.toLowerCase();
//		try {
//			int res = stmt.executeUpdate("INSERT INTO `moo`.`players` ( `name`) VALUES ('"+name + "')");
//			return getPlayerByName(name);
//			
//		}catch(SQLException e) {
//			return Optional.empty();
//		}
//	
//	}
	
	
	public abstract Optional<List<PlayerAverage>> getTop10();
	public abstract boolean insertNewResult(int result, int id);
	
}
