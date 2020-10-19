package application.testSuite;

import application.integration.AbstractGameDAO;

public class MooMockDAO extends AbstractGameDAO {

	@Override
	public String getTop10() {
		return "Top 10: ";
	}

	@Override
	public void insertNewResult(int result, int id) {
		
	}

}
