package model.player;

import java.io.Serializable;
import model.League;

public class SharkPlayer extends Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SharkPlayer(String name) {
		super(name);
		setLeague(League.SHARK);
	}
}