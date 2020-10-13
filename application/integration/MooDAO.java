package application.integration;

import application.model.bo.Player;

public interface MooDAO {
	
	public Player getPlayerById();
	public Player getPlayerByName();
	public Player getTop10();
	public Player createNewPlayer(Player player);
}
