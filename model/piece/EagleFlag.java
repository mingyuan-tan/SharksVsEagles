package model.piece;

import java.util.ArrayList;

import model.League;
import model.tile.Tile;

public class EagleFlag implements Piece {

	private final PieceType TYPE = PieceType.FLAG;
	private final League LEAGUE = League.EAGLE;
	private final int POWER_ORI = 0;
	private final int MOVEMENT_ORI = 0;
	private int power = POWER_ORI;
	private int movement = MOVEMENT_ORI;
	private boolean canAttack = false;

	@Override
	public PieceType getTYPE() {
		return TYPE;
	}

	@Override
	public League getLEAGUE() {
		return LEAGUE;
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Tile selectedTile) {
		return null;
	}

	@Override
	public int getPOWER_ORI() {
		return POWER_ORI;
	}

	@Override
	public int getMOVEMENT_ORI() {
		return MOVEMENT_ORI;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getMovement() {
		return movement;
	}

	@Override
	public boolean getCanAttack() {
		return canAttack;
	}

	@Override
	public boolean getHasEnemyFlag() {
		return false;
	}

	@Override
	public String getSkill() {
		return "";
	}

	@Override
	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public void setMovement(int movement) {
		this.movement = movement;
	}

	@Override
	public boolean getSkillUsed() {
		return false;
	}

	@Override
	public String getMode() {
		return "";
	}

	@Override
	public void setCanAttack(boolean attack) {
	}

	@Override
	public void setHasEnemyFlag(boolean flag) {
	}

	@Override
	public void setMode(String mode) {
	}

	@Override
	public void setSkillUsed() {
	}

	@Override
	public void useSkill(int pow, int mvm) {
	}
}
