package view;

import controller.MenuController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuView extends GridPane {
	private static final Font fONT_TITLE = Font.font("silom", 30);
	private static final Font fONT_INPUT = Font.font("silom", 20);
	private final MenuController controller;

	public MenuView(MenuController controller) {
		this.controller = controller;
		buildLayout();
		buildComponents();
	}

	private void buildLayout() {
		setPadding(new Insets(20, 50, 20, 50));
		setAlignment(Pos.TOP_CENTER);
		setHgap(40);
		setVgap(40);
	}

	private void buildComponents() {

		Text title = new Text("Sharks vs Eagles");
		title.setFont(fONT_TITLE);
		setHalignment(title, HPos.CENTER);

		Button newGameButton = new Button("New Game");
		newGameButton.setFont(fONT_INPUT);
		newGameButton.setMinWidth(200);
		setHalignment(newGameButton, HPos.CENTER);
		newGameButton.setOnMouseClicked(e -> controller.onClickNewGameButton());

		Button resumeGameButton = new Button("Resume Game");
		resumeGameButton.setFont(fONT_INPUT);
		resumeGameButton.setMinWidth(200);
		setHalignment(resumeGameButton, HPos.CENTER);
		resumeGameButton.setOnMouseClicked(e -> controller.onClickResumeGameButton());

		add(title, 0, 0, 3, 1);
		add(newGameButton, 0, 4, 3, 1);
		add(resumeGameButton, 0, 5, 3, 1);
	}
}
