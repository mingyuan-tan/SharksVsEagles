package controller;

import model.Game;
import view.GameView;

// Controls the game
public class GameController {

	// Grabs info from model and view
	private final Game model;
	private final GameView view;

	public GameController(Game model) {
		this.model = model;
		this.view = new GameView(this, model);
	}

	public GameView getView() {
		return view;
	}

}
