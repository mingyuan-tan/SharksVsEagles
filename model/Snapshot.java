package model;

import model.piece.Piece;
import model.tile.Tile;

public class Snapshot {

	private Board board;
	private Tile fromTile;
	private Tile toTile;
	private Piece fromPiece;
	private Piece toPiece;

	public Snapshot(Board board, Piece fromPiece, Piece toPiece) {
		this.board = board;
		this.fromTile = board.getFromTile();
		this.toTile = board.getToTile();
		this.fromPiece = fromPiece;
		this.toPiece = toPiece;
	}

	// sets the tiles in the board back to this snapshot
	public void restore() {
		fromTile.setPiece(fromPiece);
		toTile.setPiece(toPiece);
	}
}
