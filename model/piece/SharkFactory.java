package model.piece;

import model.tile.Tile;

//This class, when instantiated, will create the Shark pieces and place them on the correct position in the board.
public class SharkFactory extends AbstractFactory {

	public SharkFactory(Tile[][] tiles) {
		super(tiles);

		createGreatWhiteShark();
		createHammerHeadShark();
		createTigerShark();
	}

	public void createHammerHeadShark() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Shark")
						&& getTiles()[row][col] == getTiles()[getSize() - 2][col]) {
					getTiles()[row][col].setPiece(new HammerheadShark());
				}
			}
		}
	}

	public void createGreatWhiteShark() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Shark")
						&& (getTiles()[row][col] == getTiles()[getSize() - 1][getSize() - 1]
								|| getTiles()[row][col] == getTiles()[getSize() - 1][getSize() - 2])) {
					getTiles()[row][col].setPiece(new GreatWhiteShark());
				}
			}
		}
	}

	public void createTigerShark() {
		for (int row = 0; row < getTiles().length; row++) {
			for (int col = 0; col < getTiles()[row].length; col++) {
				if (getTiles()[row][col].getTer().territoryName().equals("Shark")
						&& (getTiles()[row][col] == getTiles()[getSize() - 1][0]
								|| getTiles()[row][col] == getTiles()[getSize() - 1][1])) {
					getTiles()[row][col].setPiece(new TigerShark());
				}
			}
		}
	}
}
