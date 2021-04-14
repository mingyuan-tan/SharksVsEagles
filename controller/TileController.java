package controller;

import javafx.scene.Node;
import model.Game;
import model.League;
import model.MoveCommand;
import model.SelectCommand;
import model.tile.Tile;
import view.BoardView;
import view.TileView;

public class TileController {

	// Delegate the model
	private final Tile model;
	private final TileView view;

	public TileController(Tile model) {
		this.model = model;
		view = new TileView(this, model);
		view.setOnMouseClicked(e -> onClickTileView());
	}

	public TileView getView() {
		return view;
	}

	public void onClickTileView() {
		var select = new SelectCommand(Game.getInstance().getBoard(), model);
		var move = new MoveCommand(Game.getInstance().getBoard(), model);

		var undoSharkCount = Game.getInstance().getBoard().getUndoCountShark();
		var undoEagleCount = Game.getInstance().getBoard().getUndoCountEagle();

		// If a piece has been selected, move the piece
		if (move.execute()) {

			if (Game.getInstance().getCurrentTurn() == League.SHARK && undoSharkCount < 3) {
				Game.getInstance().getBoard().setSharkUndoBlocked(true);
			} else if (Game.getInstance().getCurrentTurn() == League.EAGLE && undoEagleCount < 3) {
				Game.getInstance().getBoard().setEagleUndoBlocked(true);
			}

			((BoardView) view.getParent()).refresh();
			Game.getInstance().changeTurn();

			return;
		}

		// If a piece has not been selected, select a piece
		((BoardView) view.getParent()).refresh();

		if (select.execute()) {
			// Highlight the selected tile
			view.highlightAsSelected();

			// Highlight the possible moves for the selected tile
			if (Game.getInstance().getBoard().getMovableTiles() != null) {
				for (Tile possibleMove : Game.getInstance().getBoard().getMovableTiles()) {
					for (Node node : ((BoardView) view.getParent()).getChildren()) {
						if (BoardView.getRowIndex(node) == possibleMove.getRow()
								&& BoardView.getColumnIndex(node) == possibleMove.getCol()) {
							((TileView) node).highlightAsMovable();

							break;
						}
					}
				}
			}
		}
	}
}
