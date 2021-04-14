package model.piece;

// Decorator Pattern for Piece
public abstract class PieceModeDecorator implements Piece {
	protected Piece decoratedPiece;

	public PieceModeDecorator(Piece decoratedPiece) {
		this.decoratedPiece = decoratedPiece;
	}
}
