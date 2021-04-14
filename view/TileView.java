package view;

import controller.TileController;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.League;
import model.tile.Tile;

public class TileView extends StackPane {

	// Grabs the info from the controller and the model
	private final TileController controller;
	private final Tile model;

	// View components
	private Rectangle background;
	private Text text;

	public TileView(TileController controller, Tile model) {
		this.controller = controller;
		this.model = model;

		buildBackground();
		buildText();
		refreshText();
	}

	// sets the different background colors for the tiles on the board that
	// represent the land for sharks and eagles
	private void buildBackground() {
		background = new Rectangle(50, 50, Color.GREY);

		if (model.getTer().territoryName().equals("Eagle")) {
			background.setFill(Color.web("#816b3c"));
		}

		if (model.getTer().territoryName().equals("Shark")) {
			background.setFill(Color.web("#2741a1"));
		}

		if (model.getTileType().equals("Obstacle") && model.getTer().territoryName().equals("Eagle")) {
			background.setFill(Color.TOMATO);
		}

		if (model.getTileType().equals("Obstacle") && model.getTer().territoryName().equals("Shark")) {
			background.setFill(Color.PURPLE);
		}

		if (model.getTileType().equals("Obstacle") && model.getTer().territoryName().equals("Neutral")) {
			background.setFill(Color.BLACK);
		}

		if (model.getTileType().equals("PowerUp") && model.getTer().territoryName().equals("Eagle")) {
			background.setFill(Color.FORESTGREEN);
		}

		if (model.getTileType().equals("PowerUp") && model.getTer().territoryName().equals("Shark")) {
			background.setFill(Color.AQUAMARINE);
		}

		if (model.getTileType().equals("PowerUp") && model.getTer().territoryName().equals("Neutral")) {
			background.setFill(Color.LIGHTPINK);
		}

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		background.setStrokeType(StrokeType.INSIDE);
		background.setStrokeWidth(2);
		background.setArcWidth(15.0);
		background.setArcHeight(15.0);
		background.setEffect(dropShadow);
		getChildren().add(background);

	}

	private void buildText() {
		text = new Text();
		text.setFont(Font.font("STHeiti", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		text.setFill(Color.LIGHTGRAY);
		getChildren().add(text);
	}

	private void refreshText() {
		if (model.getPiece() != null) {
			text.setText(PieceDisplay.getPieceDisplay(model.getPiece()));
			text.setFont(Font.font("STHeiti", FontWeight.BOLD, FontPosture.REGULAR, 30));
			text.setFill(model.getPiece().getLEAGUE() == League.EAGLE ? Color.GOLD : Color.LIGHTBLUE);
		} else {
			text.setText(model.toString());
			text.setFont(Font.font("STHeiti", FontWeight.NORMAL, FontPosture.REGULAR, 15));
			text.setFill(Color.LIGHTGRAY);
		}
	}

	public void setPieceText(String newText) {
		text.setText(PieceDisplay.getPieceDisplay_temp(newText));
	}

	public Tile getTile() {
		return model;
	}

	public void highlight() {
		background.setStroke(Color.LIGHTSALMON);
		text.setFill(Color.LIGHTSALMON);
	}

	public void highlightAsSelected() {
		background.setStroke(Color.INDIANRED);
		text.setFill(Color.INDIANRED);
	}

	public void highlightAsMovable() {
		background.setStroke(Color.LIGHTSALMON);
		text.setFill(Color.LIGHTSALMON);
	}

	public void reset() {
		background.setStroke(Color.TRANSPARENT);
		refreshText();
	}
}
