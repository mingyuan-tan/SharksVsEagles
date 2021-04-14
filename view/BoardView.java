package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import model.Board;

import controller.BoardController;
import controller.TileController;

public class BoardView extends GridPane {

	// Grabs the info from the controller and the model
	private final BoardController controller;
	private final Board model;

	public BoardView(BoardController controller, Board model) {
		this.controller = controller;
		this.model = model;

		buildLayout();
		buildComponents();
	}

	// Generates the board
	private void buildLayout() {
		setPadding(new Insets(0, 20, 0, 20));
		setVgap(5);
		setHgap(5);
		setAlignment(Pos.TOP_CENTER);
	}

	// generates the tiles on the board
	private void buildComponents() {
		for (int x = 0; x < model.getBoard().length; x++) {
			for (int y = 0; y < model.getBoard()[0].length; y++) {
				TileView tileView = (new TileController(model.getBoard()[x][y])).getView();

				add(tileView, tileView.getTile().getCol(), tileView.getTile().getRow());
			}
		}
	}

	public void refresh() {
		for (Node node : getChildren()) {
			((TileView) node).reset();
		}
	}
}
