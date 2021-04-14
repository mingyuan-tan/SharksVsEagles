package model.player;

import java.io.Serializable;
import java.util.ArrayList;

import model.League;
import model.piece.Piece;

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private League league;
	private ArrayList<Piece> pieceSet;
	private Piece selectedPiece;
	
	public Player(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Piece> getPieceSet() {
		return pieceSet;
	}
	
	public void setPieceSet(ArrayList<Piece> pieceSet) {
		this.pieceSet = pieceSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public League getLeague() {
		return league;
	}


	public void setLeague(League league) {
		this.league = league;
	}

}