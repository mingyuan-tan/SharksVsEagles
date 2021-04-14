package model.piece;

import java.io.Serializable;
import java.util.ArrayList;

import model.League;
import model.tile.Tile;

// interface to create a piece
public interface Piece extends Serializable {

	public enum PieceType {
		GOLDEN_EAGLE("Golden Eagle"), HARPY_EAGLE("Harpy Eagle"), CROWN_EAGLE("Crown Eagle"),
		HAMMERHEAD_SHARK("Hammerhead Shark"), TIGER_SHARK("Tiger Shark"), GREATWHITE_SHARK("Great White Shark"),
		FLAG("Flag");

		private final String text;

		/**
		 * @param text
		 */
		PieceType(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	String toString();

	ArrayList<Tile> getPossibleMoves(Tile selectedTile);

	PieceType getTYPE();

	League getLEAGUE();

	int getPOWER_ORI();

	int getMOVEMENT_ORI();

	int getPower();

	int getMovement();

	String getMode();

	boolean getCanAttack();

	boolean getHasEnemyFlag();

	String getSkill();

	boolean getSkillUsed();

	void setPower(int power);

	void setMovement(int movement);

	void setMode(String mode);

	void setCanAttack(boolean attack);

	void setHasEnemyFlag(boolean flag);

	void setSkillUsed();

	void useSkill(int pow, int mvm);
}