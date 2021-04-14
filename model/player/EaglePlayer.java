package model.player;

import java.io.Serializable;
import model.League;

public class EaglePlayer extends Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EaglePlayer(String name) {
		super(name);
		setLeague(League.EAGLE);
	}
}