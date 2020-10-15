package application.model.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hedma
 *
 */
public class Player {

	private int id;
	private String name;
	private List<Integer> highscores;
	public Player(int id, String name, ArrayList<Integer> highscores) {
		this.id = id;
		this.name = name;
		this.highscores = highscores;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", highscores=" + highscores + "]";
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Integer> getHighscores() {
		return highscores;
	}
	
	
	
}
