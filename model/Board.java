package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import model.piece.FactoryProducer;
import model.piece.Piece;
import model.piece.Piece.PieceType;
import model.tile.*;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile[][] tiles;
	private boolean pieceSelected = false;
	private Tile fromTile = null;
	private Tile toTile = null;
	private ArrayList<Tile> movableTiles;
	private Territory neutral, shark, eagle;
	private int undoCountShark = 3;
	private int undoCountEagle = 3;
	private boolean sharkUndoBlocked = false;
	private boolean eagleUndoBlocked = false;

	private Piece selectedPiece;
	private PropertyChangeSupport support;

	public Board(int size, boolean obstacle) {
		buildBoard(size, obstacle);
		support = new PropertyChangeSupport(this);
		setFromTile(null);
	}

	public void buildBoard(int size, boolean obstacle) {
		// Initialize the 2D array
		tiles = new Tile[size][size];
		neutral = new Neutral("Neutral");
		shark = new Shark("Shark");
		eagle = new Eagle("Eagle");

		// Generate the tiles on the board
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				// Eagle territory (first two rows on the board)
				// assuming they are all normal tiles
				if (row < 2) {
					Tile eagleTerritoryTile = new Normal(eagle, "Normal", row, col);
					tiles[row][col] = eagleTerritoryTile;
					continue;
				}
				// Shark territory (last two rows on the board)
				// assuming they are all normal tiles
				if (row > size - 3) {
					Tile sharkTerritoryTile = new Normal(shark, "Normal", row, col);
					tiles[row][col] = sharkTerritoryTile;
					continue;
				}

				// Neutral land (rest of the rows on the board)
				// assuming they are all normal tiles
				Tile neutralTerritoryTile = new Normal(neutral, "Normal", row, col);
				tiles[row][col] = neutralTerritoryTile;
			}
		}

		// random obstacles
		if (obstacle == true) {
			for (int i = 0; i < 3; i++) {
				// generates obstacles on random tiles
				int obsRowEagle = generateRandomIntRange(1, 2);
				int obsColEagle = generateRandomIntRange(0, size - 1);
				Tile eagleTerritoryObs = new Obstacle(eagle, "Obstacle", obsRowEagle, obsColEagle);
				tiles[obsRowEagle][obsColEagle] = eagleTerritoryObs;

				int obsRowShark = generateRandomIntRange(size - 3, size - 2);
				int obsColShark = generateRandomIntRange(0, size - 1);
				Tile sharkTerritoryObs = new Obstacle(shark, "Obstacle", obsRowShark, obsColShark);
				tiles[obsRowShark][obsColShark] = sharkTerritoryObs;

				int obsRowNeutral = generateRandomIntRange(2, size - 3);
				int obsColNeutral = generateRandomIntRange(0, size - 1);
				Tile neutralTerritoryObs = new Obstacle(neutral, "Obstacle", obsRowNeutral, obsColNeutral);
				tiles[obsRowNeutral][obsColNeutral] = neutralTerritoryObs;

				// generates power-up on random tiles
				for (int j = 0; j < 2; j++) {
					int puRowEagle = generateRandomIntRange(1, 2);
					int puColEagle = generateRandomIntRange(0, size - 1);
					Tile eagleTerritoryPU = new PowerUp(eagle, "PowerUp", puRowEagle, puColEagle);
					tiles[puRowEagle][puColEagle] = eagleTerritoryPU;

					int puRowShark = generateRandomIntRange(size - 3, size - 2);
					int puColShark = generateRandomIntRange(0, size - 1);
					Tile sharkTerritoryPU = new PowerUp(shark, "PowerUp", puRowShark, puColShark);
					tiles[puRowShark][puColShark] = sharkTerritoryPU;

					int puRowNeutral = generateRandomIntRange(2, size - 3);
					int puColNeutral = generateRandomIntRange(0, size - 1);
					Tile neutralTerritoryPU = new PowerUp(neutral, "PowerUp", puRowNeutral, puColNeutral);
					tiles[puRowNeutral][puColNeutral] = neutralTerritoryPU;
				}
			}
		}

		// Places the piece in the correct tile coordinate (Abstract Factory Pattern)
		placePiece();
	}

	private static int generateRandomIntRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	// calls the piece creation methods at once (Abstract Factory Pattern)
	private void placePiece() {
		createPiece(League.EAGLE);
		createPiece(League.SHARK);
		createFlag(PieceType.FLAG);
	}

	// calls the appropriate factories that will in turn create the correct pieces (Abstract Factory Pattern) 
	private void createPiece(League league) {
		if (league == League.SHARK) {
			FactoryProducer.getFactory(league, tiles, null);
		} else if (league == League.EAGLE) {
			FactoryProducer.getFactory(league, tiles, null);
		}
	}

	private void createFlag(PieceType type) {
		FactoryProducer.getFactory(null, tiles, type);
	}

	// creates a new snapshot of board state for save state and undo purposes (Memento Pattern)
	public Snapshot createSnapshot() {
		return new Snapshot(this, fromTile.getPiece(), toTile.getPiece());
	}

	public Tile[][] getBoard() {
		return tiles;
	}

	// Pops the snapshot out of the history list and restore the state (Memento Pattern) 
	public void undo() {
		var command = CommandHistory.getInstance().pop();

		if (command != null) {
			command.restore();
		}
	}

	public ArrayList<Tile> getMovableTiles() {
		return movableTiles;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public boolean isPieceSelected() {
		return pieceSelected;
	}

	public void setPieceSelected(boolean pieceSelected) {
		this.pieceSelected = pieceSelected;
	}

	public Tile getFromTile() {
		return fromTile;
	}

	public void setFromTile(Tile fromTile) {
		this.fromTile = fromTile;

		if (fromTile != null) {
			setSelectedPiece(fromTile.getPiece());
		} else {
			setSelectedPiece(null);
		}
	}

	public void setSelectedPiece(Piece newPiece) {
		support.firePropertyChange("selectedPiece", selectedPiece, newPiece);
		this.selectedPiece = newPiece;
	}

	public Tile getToTile() {
		return toTile;
	}

	public void setToTile(Tile toTile) {
		this.toTile = toTile;
	}

	public void setMovableTiles(ArrayList<Tile> movableTiles) {
		this.movableTiles = movableTiles;
	}

	public int getUndoCountShark() {
		return undoCountShark;
	}

	public void setUndoCountShark(int undoCountShark) {
		this.undoCountShark = undoCountShark;
	}

	public int getUndoCountEagle() {
		return undoCountEagle;
	}

	public void setUndoCountEagle(int undoCountEagle) {
		this.undoCountEagle = undoCountEagle;
	}

	public boolean isSharkUndoBlocked() {
		return sharkUndoBlocked;
	}

	public void setSharkUndoBlocked(boolean sharkUndoBlocked) {
		this.sharkUndoBlocked = sharkUndoBlocked;
	}

	public boolean isEagleUndoBlocked() {
		return eagleUndoBlocked;
	}

	public void setEagleUndoBlocked(boolean eagleUndoBlocked) {
		this.eagleUndoBlocked = eagleUndoBlocked;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
}