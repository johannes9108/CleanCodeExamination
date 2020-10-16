package application.model;

public abstract class AbstractGame{
	
	protected String goal = "";
	private int guesses = 0;
	protected String winCondition;
	public String getWinCondition() {
		return winCondition;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public int getGuesses() {
		return guesses;
	}
	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}
	
	public abstract void generateAnswer();
	public abstract String generateProgress(String guess);
	public abstract boolean validate(String guess);
	

}
