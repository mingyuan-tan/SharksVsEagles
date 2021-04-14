package model.piece;

import java.util.ArrayList;

import model.League;
import model.tile.Tile;

// Normal Mode decorator resets the piece's original stats
public class NormalMode extends PieceModeDecorator {
	public NormalMode(Piece decoratedPiece) {
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
		decoratedPiece.setMode("Normal");
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
		return decoratedPiece.getPossibleMoves(selectedTile);
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
		return decoratedPiece.getMovement();
	}

	@Override
	public String getMode() {
		return "Normal";
	}

	@Override
	public boolean getCanAttack() {
		return decoratedPiece.getCanAttack();
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
	public boolean getSkillUsed() {
		return decoratedPiece.getSkillUsed();
	}

	@Override
	public String getSkill() {
		return decoratedPiece.getSkill();
	}

	@Override
	public void useSkill(int pow, int mvm) {
		decoratedPiece.useSkill(pow, mvm);
	}
}
