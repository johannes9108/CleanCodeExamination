package application.ui;

public interface UI {

	String getString();

	void addString(String s);

	void clear();

	void exit();

	int optionPaneAnswer();

	int keepPlayingDialog(String msg);

}