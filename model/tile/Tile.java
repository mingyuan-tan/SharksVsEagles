package model.tile;

import java.io.Serializable;

import model.League;
import model.piece.Piece;

public abstract class Tile implements Serializable {
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private League territory;
	private Piece piece = null;

	public String tileType;
	public Territory ter;

	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Tile clone() throws CloneNotSupportedException {
		Tile clonedObj = (Tile) super.clone();
		return clonedObj;
	}

	public Tile(int row, int col, League territory) {
		this.row = row;
		this.col = col;
		this.territory = territory;
	}

	public Tile(Territory territory, String tileType, int row, int col) {
		this.ter = territory;
		this.tileType = tileType;
		this.row = row;
		this.col = col;
	}

	public abstract void createTile();

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public League getTerritory() {
		return territory;
	}

	public Territory getTer() {
		return ter;
	}

	public String getTileType() {
		return tileType;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public String toString() {
		return "(" + row + ", " + col + ")";
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Tile that = (Tile) o;
		return row == that.row && col == that.col;
	}
}