package controller;

import model.Board;
import view.BoardView;

// Controls the board
public class BoardController {

	// Grabs info from model and view
	private final Board model;
	private final BoardView view;

	public BoardController(Board model) {
		this.model = model;
		this.view = new BoardView(this, model);

	}

	public BoardView getView() {
		return view;
	}
}
