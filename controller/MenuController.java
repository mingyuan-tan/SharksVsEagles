package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Game;
import model.ResourceManager;
import model.SaveData;
import view.EndGameView;
import view.MenuView;
import view.NewGameView;

// Controls the game
public class MenuController implements PropertyChangeListener {
	private Parent view;
	private Game model;
	private Stage popUp;

	public MenuController() {
		view = new MenuView(this);
	}

	public Parent getView() {
		return view;
	}

	public void onClickNewGameButton() {
		switchScene(new NewGameView(this));
	}

	public void onClickBackToMenuButton() {
		popUp.hide();
		switchScene(new MenuView(this));
	}

	public void onClickGoButton(Object p1, Object p2, Object size, Object obstacle) {
		// Sent the parameters to create new game

		Game.Builder builder = new Game.Builder().size(size).obstacle(obstacle).eaglePlayer(p1).sharkPlayer(p2);

		model = Game.getInstance(builder);

		GameController gameController = new GameController(model);
		model.addPropertyChangeListener(this);
		switchScene(gameController.getView());
	}

	public void onClickResumeGameButton() {
		try {
			SaveData data = (SaveData) ResourceManager.load("sve.save");
			model = data.game;

		} catch (Exception e) {
			System.out.println("Couldn't load save data: " + e.getMessage());
		}
		GameController gameController = new GameController(model);
		model.addPropertyChangeListener(this);
		switchScene(gameController.getView());
	}

	private void switchScene(Parent nextView) {
		Scene scene = new Scene(nextView);
		Stage stage = (Stage) view.getScene().getWindow();
		stage.setScene(scene);
		stage.centerOnScreen();

		view = nextView;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName() + " is fired");
		if (model.isGameOver()) {
			Scene scene = new Scene(new EndGameView(this));
			popUp = new Stage();
			popUp.initModality(Modality.APPLICATION_MODAL);
			popUp.initOwner((Stage) view.getScene().getWindow());
			popUp.setScene(scene);
			popUp.show();
		}
	}
}
