package application.integration;

import java.util.List;
import java.util.Optional;

import application.model.bo.Player;

public interface DAO {
	
	public Optional<Player> getPlayerByName(String name);
	public String getTop10();
	public void insertNewResult(int result, int id);
	
}
