package model.piece;

import model.tile.Tile;

// This class, when instantiated, will create the Eagle pieces and place them on the correct position in the board.
public class EagleFactory extends AbstractFactory {

	public EagleFactory(Tile[][] tiles) {
		super(tiles);
		createCrownEagle();
		createHarpyEagle();
		createGoldenEagle();
	}

	public void createCrownEagle() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Eagle")
						&& (getTiles()[row][col] == getTiles()[0][0] || getTiles()[row][col] == getTiles()[0][1])) {
					getTiles()[row][col].setPiece(new CrownEagle());
				}
			}
		}
	}

	public void createHarpyEagle() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Eagle") &&
						(getTiles()[row][col] == getTiles()[0][getSize() - 1]
								|| getTiles()[row][col] == getTiles()[0][getSize() - 2])) {
					getTiles()[row][col].setPiece(new HarpyEagle());
				}
			}
		}
	}

	public void createGoldenEagle() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Eagle")
						&& getTiles()[row][col] == getTiles()[1][col]) {
					getTiles()[row][col].setPiece(new GoldenEagle());
				}
			}
		}
	}
}
