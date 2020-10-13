package application.model;

import application.ui.UI;

public class Controller {

	private UI ui;
	// EXTRACT TILL GENERISK LOGIKKOMPONENT
	private MooLogic logic;
	
	public Controller(UI ui, MooLogic logic) {
		this.ui = ui;
		this.logic = logic;
	}

	public void startApplication() {
		logic.run(ui);
		
	}

}
