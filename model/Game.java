package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import model.player.EaglePlayer;
import model.player.Player;
import model.player.SharkPlayer;

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int size;
	private boolean obstacle;
	private Board board;
	private Player eaglePlayer;
	private Player sharkPlayer;
	private boolean gameOver;
	private League currentTurn;
	private PropertyChangeSupport support;

	private static Game instance;

	private Game(Builder builder) {
		this.size = builder.size;
		this.obstacle = builder.obstacle;
		this.eaglePlayer = builder.eaglePlayer;
		this.sharkPlayer = builder.sharkPlayer;
		this.board = new Board(size, obstacle);
		this.gameOver = false;
		this.currentTurn = League.EAGLE;
		this.support = new PropertyChangeSupport(this);
	}

	// Singleton Pattern to ensure only one instance of Game object is created 
	public static synchronized Game getInstance(Builder builder) {
		if (instance == null) {
			instance = builder.build();
		}
		return instance;
	}

	public static synchronized Game getInstance() {
		if (instance == null) {
			instance = new Game(new Builder());
		}
		return instance;
	}

	protected Object readResolve() {
		return getInstance();
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		instance = this;
	}

	public Board getBoard() {
		return board;
	}

	public League getCurrentTurn() {
		return currentTurn;
	}

	public void changeTurn() {
		if (currentTurn == League.SHARK) {
			setCurrentTurn(League.EAGLE);
			return;
		}
		setCurrentTurn(League.SHARK);
	}

	public void setCurrentTurn(League newTurn) {
		support.firePropertyChange("currentTurn", currentTurn, newTurn);
		currentTurn = newTurn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean newGameOver) {
		support.firePropertyChange("gameOver", gameOver, newGameOver);
		this.gameOver = newGameOver;
	}

	public Player getEaglePlayer() {
		return eaglePlayer;
	}

	public void setEaglePlayer(Player eaglePlayer) {
		this.eaglePlayer = eaglePlayer;
	}

	public Player getSharkPlayer() {
		return sharkPlayer;
	}

	public void setSharkPlayer(Player sharkPlayer) {
		this.sharkPlayer = sharkPlayer;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public boolean isInBoardRange(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			return false;
		}
		return true;
	}

	public static class Builder {
		private int size = 10;
		private boolean obstacle = false;
		private Player eaglePlayer;
		private Player sharkPlayer;

		public Builder size(Object size) {
			if ((int) size <= 0) {
				throw new IllegalArgumentException();
			}
			this.size = (int) size;
			return this;
		}

		public Builder obstacle(Object obstacle) {
			this.obstacle = (boolean) obstacle;
			return this;
		}

		public Builder eaglePlayer(Object name) {
			if (name != null) {
				this.eaglePlayer = new EaglePlayer((String) name);
			} else {
				this.eaglePlayer = new EaglePlayer("Eagle Player");
			}
			return this;
		}

		public Builder sharkPlayer(Object name) {
			if (name != null) {
				this.sharkPlayer = new SharkPlayer((String) name);
			} else {
				this.sharkPlayer = new SharkPlayer("Shark Player");
			}

			return this;
		}

		public Game build() {
			Game game = new Game(this);
			return game;
		}
	}
}