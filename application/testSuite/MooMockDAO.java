package application.testSuite;

import java.util.List;
import java.util.Optional;

import application.integration.AbstractGameDAO;
import application.model.PlayerAverage;

public class MooMockDAO extends AbstractGameDAO {

	@Override
	public Optional<List<PlayerAverage>> getTop10() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertNewResult(int result, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
