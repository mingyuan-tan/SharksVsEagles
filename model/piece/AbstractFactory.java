package model.piece;

import model.tile.Tile;

public abstract class AbstractFactory {
	private Tile[][] tiles;
	private int size;

	public AbstractFactory(Tile[][] tiles) {
		this.tiles = tiles;
		size = tiles.length;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getSize() {
		return size;
	}
}
