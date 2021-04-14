package model;

import model.piece.Piece;
import model.tile.Tile;

public class SelectCommand extends Command {

	public SelectCommand(Board board, Tile tile) {
		super(board, tile);
	}

	// selects a piece on the board and sets the possible moves
	@Override
	public boolean execute() {
		Piece piece = tile.getPiece();

		if (Game.getInstance().isGameOver() == true) {
			return false;
		} else {
			// check whether the tile has a piece on it
			if (piece == null) {
				return false;
			}

			// check if it's their turn
			if (piece.getLEAGUE() != Game.getInstance().getCurrentTurn()) {
				return false;
			}

			getBoard().setFromTile(tile);
			getBoard().setPieceSelected(true);
			getBoard().setMovableTiles(piece.getPossibleMoves(tile));
		}
		return true;
	}
}