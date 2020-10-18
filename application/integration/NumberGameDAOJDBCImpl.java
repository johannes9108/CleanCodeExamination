package application.integration;

import java.sql.SQLException;

public class NumberGameDAOJDBCImpl extends AbstractGameDAO {
	
	

	@Override
	public String getTop10() {
		StringBuilder builder = new StringBuilder();
		int counter = 1;
		try {
			stmt = connection.prepareStatement("SELECT name,avg(result) as average FROM players\r\n"
					+ "join numberGame_results\r\n"
					+ "on players.id = numberGame_results.player\r\n"
					+ "group by name\r\n"
					+ "order by average asc\r\n"
					+ "limit 10;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				builder.append(counter++ +":" + rs.getString(1) + " " + rs.getDouble(2)+"\n");
			}
		}catch(SQLException e) {
			return "";
		}
		return builder.toString();
	}

	@Override
	public void insertNewResult(int result, int id) {
try {
			
			stmt = connection.prepareStatement("INSERT INTO numberGame_results (result, player) VALUES (?, ?)" );
			stmt.setInt(1, result);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
