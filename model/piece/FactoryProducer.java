package model.piece;

import model.League;
import model.piece.Piece.PieceType;
import model.tile.Tile;

public class FactoryProducer {

	public static AbstractFactory getFactory(League league, Tile[][] tiles, PieceType flagType) {
		if (league == League.EAGLE) {
			return new EagleFactory(tiles);
		} else if (league == League.SHARK) {
			return new SharkFactory(tiles);
		} else if (flagType != null) {
			return new FlagFactory(tiles);
		} else {
			return null;
		}
	}
}
