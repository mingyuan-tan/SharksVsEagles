package model.piece;

import model.tile.Tile;

//This class, when instantiated, will create the respective league flag pieces and place them on the correct position in the board.
public class FlagFactory extends AbstractFactory {

	public FlagFactory(Tile[][] tiles) {
		super(tiles);

		createEagleFlag();
		createSharkFlag();
	}
	
	public void createEagleFlag() {
		for (int col = 0; col < getTiles()[0].length; col++) {
			if (getTiles()[0][col].getTer().territoryName().equals("Eagle") && 
					getTiles()[0][col] == getTiles()[0][getSize() / 2]) {
				
				if (getTiles()[0][col].getPiece() == null) {
					getTiles()[0][col].setPiece(new EagleFlag());
				}
			}
		}
	}

	public void createSharkFlag() {
		int lastRow = getTiles().length - 1;
		
		for (int col = 0; col < getTiles()[lastRow].length; col++) {
			if (getTiles()[lastRow][col].getTer().territoryName().equals("Shark") && 
					getTiles()[lastRow][col] == getTiles()[getSize() - 1][(getSize() / 2) - 1]) {
				getTiles()[lastRow][col].setPiece(new SharkFlag());
			}
		}
	}
}
