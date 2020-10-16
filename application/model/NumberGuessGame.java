package application.model;

import java.util.Random;

public class NumberGuessGame extends AbstractGame {

	
	{
		this.winCondition = "You win";
		
	}
	@Override
	public void generateAnswer() {
		this.goal = String.valueOf(new Random().nextInt(100)+1);
	}

	@Override
	public String generateProgress(String guess) {
		int target = Integer.valueOf(goal);
		int currentGuess = Integer.valueOf(guess);
		if(target<currentGuess) {
			return "Too high";
		}
		else if(target>currentGuess) {
			return "Too low";
		}
		else
			return "You win";
	}

	@Override
	public boolean validate(String guess) {
		boolean ok = guess.matches("[0-9]+");
		return ok;
		
	}

}
