package view;

import controller.MenuController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Game;

public class EndGameView extends GridPane {
	private static final Font fONT_TITLE = Font.font("silom", 40);
	private static final Font fONT_INPUT = Font.font("silom", 30);

	private final MenuController controller;

	public EndGameView(MenuController controller) {
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

		Text title = new Text("Victory!");
		title.setFont(fONT_TITLE);
		setHalignment(title, HPos.CENTER);

		Text league = new Text(Game.getInstance().getCurrentTurn().toString());
		league.setFont(fONT_TITLE);
		setHalignment(league, HPos.CENTER);

		Button button = new Button("Start over");
		button.setFont(fONT_INPUT);
		button.setMinWidth(100);
		setHalignment(button, HPos.CENTER);

		button.setOnMouseClicked(e -> controller.onClickBackToMenuButton());

		add(title, 0, 0, 3, 1);
		add(league, 0, 1, 3, 1);
		add(button, 0, 5, 3, 1);
	}
}
