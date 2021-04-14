package view;

import controller.BoardController;
import controller.GameController;
import controller.PlayerInfoController;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import model.Game;

public class GameView extends BorderPane {

	// Grabs the info from the controller and the model
	private final GameController controller;
	private final Game model;

	// View Components
	public HeaderView topHbox;
	public PlayerInfoView leftVbox;
	public PlayerInfoView rightVbox;
	public BoardView centerGrid;

	public GameView(GameController controller, Game model) {
		this.controller = controller;
		this.model = model;

		buildLayout();
		buildComponents();
	}

	public GameController getController() {
		return controller;
	}

	private void buildLayout() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void buildComponents() {
		topHbox = new HeaderView(model);
		model.addPropertyChangeListener(topHbox);
		setTop(topHbox);

		leftVbox = (new PlayerInfoController(model.getEaglePlayer())).getView();
		leftVbox.getStylesheets().add("Stylesheet.css");
		leftVbox.getStyleClass().add("leftVbox");
		setLeft(leftVbox);

		rightVbox = (new PlayerInfoController(model.getSharkPlayer())).getView();
		rightVbox.getStylesheets().add("Stylesheet.css");
		rightVbox.getStyleClass().add("rightVbox");
		setRight(rightVbox);

		centerGrid = (new BoardController(model.getBoard()).getView());
		model.getBoard().addPropertyChangeListener(leftVbox);
		model.getBoard().addPropertyChangeListener(rightVbox);
		setCenter(centerGrid);
	}
}
