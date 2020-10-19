package application.testSuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.AbstractGame;
import application.model.Controller;
import application.model.MooGame;

class MooLogicTest {
	
	
	static Controller controller;
	static AbstractGame gameLogic;
	static String target = "1234";
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gameLogic = new MooGame();
		gameLogic.setGoal(target);
		controller = new Controller(new MooMockUI(),gameLogic,new MooMockDAO());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void checkIfAnswerIs4DigitsTest() {
		AbstractGame localLogic = new MooGame();
		assertEquals("", localLogic.getGoal());
		localLogic.generateAnswer();
		assertTrue(localLogic.getGoal().matches("[0-9]{4}"));
	}
	
	@Test
	void checkAllWrongInputTest() {
		assertEquals(",",gameLogic.generateProgress("5678"));
		assertEquals(",",gameLogic.generateProgress("9999"));
		assertEquals(",",gameLogic.generateProgress("7766"));
	}
	@Test
	void checkPartialWrongInputTest() {
		assertEquals("BBB,",gameLogic.generateProgress("1235"));
		assertEquals("B,CC",gameLogic.generateProgress("4932"));
		assertEquals(",CCC",gameLogic.generateProgress("2813"));
	}
	@Test
	void checkAllMatchInputTest() {
		assertEquals("BBBB,",gameLogic.generateProgress("1234"));
	}
	
	@Test
	void test() {
		
	}
	
	
	

}
