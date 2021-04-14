package model;

import model.tile.Tile;

public abstract class Command {

	private Board board;
	protected Tile tile; 
	private Snapshot backup; 

	public Command(Board board, Tile tile) {
		this.setBoard(board); 
		this.tile = tile;
	}
	
	public abstract boolean execute();
	
	// Creates a backup of the current state and pushes it to CommandHistory (Memento Pattern)   
	public void makeBackup() {
		backup = board.createSnapshot();
		CommandHistory.getInstance().push(backup);
	}
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}