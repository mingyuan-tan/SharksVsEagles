package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Game;
import model.League;
import model.SaveData;
import util.Utilities;

public class HeaderView extends HBox implements PropertyChangeListener {

	Game game;
	Text currentTurn;

	public HeaderView(Game game) {
		buildLayout();
		buildComponents();
		this.game = game;
	}

	
	// executes the Board's undo method when clicked. 
	public void onUndoButtonClicked(ActionEvent e) {
		var undoSharkCount = game.getBoard().getUndoCountShark();
		var undoEagleCount = game.getBoard().getUndoCountEagle();

		var eagleBlocked = game.getBoard().isEagleUndoBlocked();
		var sharkBlocked = game.getBoard().isSharkUndoBlocked();

		if (Game.getInstance().getCurrentTurn() == League.EAGLE && undoEagleCount > 0 && eagleBlocked == false) {

			game.getBoard().setUndoCountEagle(game.getBoard().getUndoCountEagle() - 1);

			// undoes the current player's and the opponent's latest moves
			game.getBoard().undo();
			game.getBoard().undo();

			((GameView) getParent()).centerGrid.refresh();

			if (game.getBoard().getUndoCountEagle() == 0) {
				game.getBoard().setEagleUndoBlocked(true);
			}
		}

		if (Game.getInstance().getCurrentTurn() == League.SHARK && undoSharkCount > 0 && sharkBlocked == false) {

			game.getBoard().setUndoCountShark(game.getBoard().getUndoCountShark() - 1);

			game.getBoard().undo();
			game.getBoard().undo();

			((GameView) getParent()).centerGrid.refresh();

			if (game.getBoard().getUndoCountShark() == 0) {
				game.getBoard().setSharkUndoBlocked(true);
			}
		}
	}

	private void buildLayout() {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
	}

	private void buildComponents() {
		buildUndoButton();
		buildTitleBox();
		buildSaveGameButton();
	}

	private void buildTitleBox() {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.DARKGRAY);

		VBox titleBox = new VBox();
		titleBox.setAlignment(Pos.TOP_CENTER);
		HBox.setHgrow(titleBox, Priority.ALWAYS);

		Text title = new Text("EAGLE V SHARK II: Revenge of Jaws");
		title.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
		title.setFill(Color.web("#373837"));
		title.setStrokeWidth(2);
		// title.setStroke(Color.BLACK);
		title.setEffect(dropShadow);

		HBox turnBox = new HBox();
		turnBox.setAlignment(Pos.TOP_CENTER);
		Label turn = new Label("IT'S YOUR MOVE ");
		turn.setFont(Font.font("silom", FontWeight.BLACK, FontPosture.REGULAR, 20));
		turn.setTextFill(Color.ORANGERED);

		currentTurn = new Text("EAGLE");
		currentTurn.setFont(Font.font("silom", FontWeight.BLACK, FontPosture.REGULAR, 20));
		currentTurn.setFill(Color.ORANGERED);

		turnBox.getChildren().add(turn);
		turnBox.getChildren().add(currentTurn);

		titleBox.getChildren().add(title);
		titleBox.getChildren().add(turnBox);

		getChildren().add(titleBox);
	}

	private void buildUndoButton() {
		Button undoButton = new Button("Undo");
		undoButton.setOnAction(e -> onUndoButtonClicked(e));
		HBox.setHgrow(undoButton, Priority.ALWAYS);
		getChildren().add(undoButton);
	}

	private void buildSaveGameButton() {
		Button saveGameButton = new Button("Save Game");
		saveGameButton.setOnAction(e -> onSaveButtonClicked(e));

		HBox.setHgrow(saveGameButton, Priority.ALWAYS);
		getChildren().add(saveGameButton);
	}

	private void onSaveButtonClicked(ActionEvent e) {
		SaveData data = new SaveData();

		data.game = game;
		data.currentTurn = game.getCurrentTurn();

		try {
			model.ResourceManager.save(data, "sve.save");
		} catch (Exception message) {
			System.out.println("Couldn't save: " + message.getMessage());
		}

		Utilities.infoAlert("Game Saved", "");
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		currentTurn.setText(evt.getNewValue().toString());
	}
}
