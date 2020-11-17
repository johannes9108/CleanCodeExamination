package application.integration;

import java.util.List;
import java.util.Optional;

import application.model.PlayerAverage;
import application.model.bo.Player;

public interface DAO {
	
	public Optional<Player> getPlayerByName(String name);
	public Optional<List<PlayerAverage>> getTop10();
	public boolean insertNewResult(int result, int id);
	
}
