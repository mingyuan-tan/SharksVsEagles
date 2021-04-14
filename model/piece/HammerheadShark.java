package model.piece;

import java.util.ArrayList;

import model.Game;
import model.League;
import model.tile.*;
import util.Utilities;

public class HammerheadShark implements Piece {

	private final PieceType TYPE = PieceType.HAMMERHEAD_SHARK;
	private final League LEAGUE = League.SHARK;
	private final int POWER_ORI = 1;
	private final int MOVEMENT_ORI = 3;
	private int power = POWER_ORI;
	private int movement = MOVEMENT_ORI;
	private boolean canAttack = true;
	private boolean hasEnemyFlag = false;
	private String mode = "Normal";
	private String skill = "Kraken Call";
	private boolean skillUsed = false;

	public HammerheadShark() {
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

		possibleMoves = Utilities.addSharkPossibleMoves(movement, row, col, num, neutral, shark, eagle);
		possibleMoves.removeIf(i -> !Game.getInstance().isInBoardRange(i.getRow(), i.getCol()));

		return possibleMoves;
	}

	@Override
	public PieceType getTYPE() {
		return TYPE;
	}

	@Override
	public League getLEAGUE() {
		return LEAGUE;
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
	public String getMode() {
		return mode;
	}

	@Override
	public boolean getCanAttack() {
		return canAttack;
	}

	@Override
	public boolean getHasEnemyFlag() {
		return hasEnemyFlag;
	}

	public String getSkill() {
		skill += "\nPower - 2" + "\nMovement + 3";
		return skill;
	}

	@Override
	public boolean getSkillUsed() {
		return skillUsed;
	}

	@Override
	public void useSkill(int pow, int mvm) {
		setPower(0);
		setMovement(mvm + 3);
		
		if(getSkillUsed() == false) {
			Utilities.infoAlert("Skill Used", getSkill());
		}
		setSkillUsed();
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
	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public void setCanAttack(boolean attack) {
		this.canAttack = attack;
	}

	@Override
	public void setHasEnemyFlag(boolean flag) {
		this.hasEnemyFlag = flag;
	}

	@Override
	public void setSkillUsed() {
		this.skillUsed = true;
	}
}
