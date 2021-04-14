package model.piece;

import java.util.ArrayList;

import model.Game;
import model.League;
import model.tile.Eagle;
import model.tile.Neutral;
import model.tile.Normal;
import model.tile.Obstacle;
import model.tile.PowerUp;
import model.tile.Shark;
import model.tile.Territory;
import model.tile.Tile;
import util.Utilities;

// Scout Mode decorator increases the piece's power by 1,
// but the piece cannot attack the opposing team.
public class ScoutMode extends PieceModeDecorator {
	private int movement = decoratedPiece.getMovement() + 1;
	private boolean canAttack = false;

	public ScoutMode(Piece decoratedPiece) {
		super(decoratedPiece);
	}

	@Override
	public void setPower(int power) {
		decoratedPiece.setPower(power);
	}

	@Override
	public void setMovement(int movement) {
		decoratedPiece.setMovement(movement);
	}

	@Override
	public void setMode(String mode) {
		decoratedPiece.setMode("Scout");
	}

	@Override
	public void setCanAttack(boolean attack) {
		decoratedPiece.setCanAttack(attack);
	}

	@Override
	public void setHasEnemyFlag(boolean flag) {
		decoratedPiece.setHasEnemyFlag(flag);
	}

	@Override
	public void setSkillUsed() {
		decoratedPiece.setSkillUsed();
	}

	@Override
	public League getLEAGUE() {
		return decoratedPiece.getLEAGUE();
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Tile selectedTile) {
		int row = selectedTile.getRow();
		int col = selectedTile.getCol();
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();

		Territory neutral = new Neutral("Neutral");
		Territory shark = new Shark("Shark");
		Territory eagle = new Eagle("Eagle");

		int num = 1;

		if (decoratedPiece.getLEAGUE() == League.EAGLE) {
				possibleMoves = Utilities.addEaglePossibleMoves(getMovement(), row, col, num, neutral, shark, eagle);
		} else if (decoratedPiece.getLEAGUE() == League.SHARK) {
			possibleMoves = Utilities.addSharkPossibleMoves(getMovement(), row, col, num, neutral, shark, eagle);

		}
		possibleMoves.removeIf(i -> !Game.getInstance().isInBoardRange(i.getRow(), i.getCol()));

		return possibleMoves;
	}

	@Override
	public PieceType getTYPE() {
		return decoratedPiece.getTYPE();
	}

	@Override
	public int getPower() {
		return decoratedPiece.getPower();
	}

	@Override
	public int getMovement() {
		if (getSkillUsed() == true) {
			return decoratedPiece.getMovement();
		} else {
			return movement;
		}
	}

	@Override
	public String getMode() {
		return "Scout";
	}

	@Override
	public boolean getCanAttack() {
		return canAttack;
	}

	@Override
	public boolean getHasEnemyFlag() {
		return decoratedPiece.getHasEnemyFlag();
	}

	@Override
	public int getPOWER_ORI() {
		return decoratedPiece.getPOWER_ORI();
	}

	@Override
	public int getMOVEMENT_ORI() {
		return decoratedPiece.getMOVEMENT_ORI();
	}

	@Override
	public String getSkill() {
		return decoratedPiece.getSkill();
	}

	@Override
	public boolean getSkillUsed() {
		return decoratedPiece.getSkillUsed();
	}

	@Override
	public void useSkill(int pow, int mvm) {
		decoratedPiece.useSkill(pow, movement);
	}
}
