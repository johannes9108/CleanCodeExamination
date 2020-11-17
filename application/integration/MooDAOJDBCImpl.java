package application.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.model.PlayerAverage;
import application.model.bo.Player;
import application.ui.UI;

public class MooDAOJDBCImpl extends AbstractGameDAO {

	@Override
	public Optional<List<PlayerAverage>> getTop10() {
		List<PlayerAverage> averageList = new ArrayList<PlayerAverage>();
		int counter = 1;
		try {
			stmt = connection.prepareStatement("SELECT name,avg(result) as average FROM players\r\n"
					+ "join moo_results\r\n" + "on players.id = moo_results.player\r\n" + "group by name\r\n"
					+ "order by average asc\r\n" + "limit 10;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				averageList.add(new PlayerAverage(rs.getString(1), rs.getDouble(2)));
			}
		} catch (SQLException e) {
			return Optional.empty();
		}
		return Optional.of(averageList);
	}

	@Override
	public boolean insertNewResult(int result, int id) {
		try {

			stmt = connection.prepareStatement("INSERT INTO numberGame_results (result, player) VALUES (?, ?)");
			stmt.setInt(1, result);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
