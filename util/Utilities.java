package util;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.tile.Normal;
import model.tile.Obstacle;
import model.tile.PowerUp;
import model.tile.Territory;
import model.tile.Tile;

public class Utilities {

	// generates and shows an alert
	public static void infoAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(msg);
		alert.show();
	}

	// adds the possible tiles that the eagle piece can move to into an arraylist
	public static ArrayList<Tile> addEaglePossibleMoves(int movement, int row, int col, int num, Territory neutral,
			Territory shark, Territory eagle) {
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();

		while (num <= movement) {
			possibleMoves.add(new Normal(neutral, "Normal", row + num, col + num));
			possibleMoves.add(new Normal(eagle, "Normal", row + num, col + num));
			possibleMoves.add(new Normal(shark, "Normal", row + num, col + num));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row + num, col + num));
			possibleMoves.add(new PowerUp(eagle, "PowerUp", row + num, col + num));
			possibleMoves.add(new Obstacle(eagle, "Obstacle", row + num, col + num));

			possibleMoves.add(new Normal(neutral, "Normal", row + num, col - num));
			possibleMoves.add(new Normal(eagle, "Normal", row + num, col - num));
			possibleMoves.add(new Normal(shark, "Normal", row + num, col - num));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row + num, col - num));
			possibleMoves.add(new PowerUp(eagle, "PowerUp", row + num, col - num));
			possibleMoves.add(new Obstacle(eagle, "Obstacle", row + num, col - num));

			possibleMoves.add(new Normal(neutral, "Normal", row - num, col + num));
			possibleMoves.add(new Normal(eagle, "Normal", row - num, col + num));
			possibleMoves.add(new Normal(shark, "Normal", row - num, col + num));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row - num, col + num));
			possibleMoves.add(new PowerUp(eagle, "PowerUp", row - num, col + num));
			possibleMoves.add(new Obstacle(eagle, "Obstacle", row - num, col + num));

			possibleMoves.add(new Normal(neutral, "Normal", row - num, col - num));
			possibleMoves.add(new Normal(eagle, "Normal", row - num, col - num));
			possibleMoves.add(new Normal(shark, "Normal", row - num, col - num));
			possibleMoves.add(new PowerUp(neutral, "Normal", row - num, col - num));
			possibleMoves.add(new PowerUp(eagle, "Normal", row - num, col - num));
			possibleMoves.add(new Obstacle(eagle, "Obstacle", row - num, col - num));

			num++;
		}
		return possibleMoves;
	}

	// adds the possible tiles that the shark piece can move to into an arraylist
	public static ArrayList<Tile> addSharkPossibleMoves(int movement, int row, int col, int num, Territory neutral,
			Territory shark, Territory eagle) {
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();

		while (num <= movement) {
			possibleMoves.add(new Normal(neutral, "Normal", row + num, col));
			possibleMoves.add(new Normal(eagle, "Normal", row + num, col));
			possibleMoves.add(new Normal(shark, "Normal", row + num, col));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row + num, col));
			possibleMoves.add(new PowerUp(shark, "PowerUp", row + num, col));
			possibleMoves.add(new Obstacle(shark, "Obstacle", row + num, col));

			possibleMoves.add(new Normal(neutral, "Normal", row - num, col));
			possibleMoves.add(new Normal(eagle, "Normal", row - num, col));
			possibleMoves.add(new Normal(shark, "Normal", row - num, col));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row - num, col));
			possibleMoves.add(new PowerUp(shark, "PowerUp", row - num, col));
			possibleMoves.add(new Obstacle(shark, "Obstacle", row - num, col));

			possibleMoves.add(new Normal(neutral, "Normal", row, col + num));
			possibleMoves.add(new Normal(eagle, "Normal", row, col + num));
			possibleMoves.add(new Normal(shark, "Normal", row, col + num));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row, col + num));
			possibleMoves.add(new PowerUp(shark, "PowerUp", row, col + num));
			possibleMoves.add(new Obstacle(shark, "Obstacle", row, col + num));

			possibleMoves.add(new Normal(neutral, "Normal", row, col - num));
			possibleMoves.add(new Normal(eagle, "Normal", row, col - num));
			possibleMoves.add(new Normal(shark, "Normal", row, col - num));
			possibleMoves.add(new PowerUp(neutral, "PowerUp", row, col - num));
			possibleMoves.add(new PowerUp(shark, "PowerUp", row, col - num));
			possibleMoves.add(new Obstacle(shark, "Obstacle", row, col - num));

			num++;
		}
		return possibleMoves;
	}
}
