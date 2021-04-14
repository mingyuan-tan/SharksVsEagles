package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//import java.awt.event.ActionEvent;

import controller.PlayerInfoController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Game;
import model.League;
import model.piece.Piece;
import model.player.Player;

public class PlayerInfoView extends VBox implements PropertyChangeListener {

	// Grabs the info from the controller and the model
	private final PlayerInfoController controller;
	private final Player model;
	Text piece;
	Text mode;
	Text power;
	Text skillDesc;

	public PlayerInfoView(PlayerInfoController controller, Player model) {
		this.controller = controller;
		this.model = model;

		buildLayout();
		buildComponents();
	}

	private void buildLayout() {
		setSpacing(20);
		setPadding(new Insets(20, 20, 20, 20));
		setAlignment(Pos.TOP_CENTER);
		setPrefWidth(350);

		// sets different colors for the background in the pane for eagles and sharks
		if (model.getLeague() == League.EAGLE) {
			setBackground(
					new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		} else {
			setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}

	private void buildComponents() {
		buildTitle();
		buildPieceBox();
	}

	private void buildTitle() {
		Text title = new Text(model.getName());
		title.setFont(Font.font("silom", FontWeight.EXTRA_BOLD, 35));
		title.setFill(Color.WHITE);
		getChildren().add(title);

		Image image;
		if (model.getLeague() == League.EAGLE) {
			image = new Image("images/Eagle.png", 225, 180, false, false);
		} else {
			image = new Image("images/Shark.png", 180, 180, false, false);
		}

		ImageView logo = new ImageView(image);
		getChildren().add(logo);
	}

	private void buildPieceBox() {
		GridPane PieceBox = new GridPane();

		PieceBox.setPadding(new Insets(20, 0, 20, 0));
		PieceBox.setVgap(20);
		PieceBox.setHgap(10);
		PieceBox.setStyle("-fx-background-color: white; -fx-background-radius: 10 10 10 10;");

		PieceBox.setAlignment(Pos.CENTER);
		PieceBox.getStylesheets().add("Stylesheet.css");

		Label label_piece = new Label("PIECE: ");
		label_piece.setStyle("-fx-font-weight: bold");
		piece = new Text();

		Label label_mode = new Label("MODE: ");
		label_mode.setStyle("-fx-font-weight: bold");
		mode = new Text();

		Label label_power = new Label("POWER: ");
		label_power.setStyle("-fx-font-weight: bold");
		power = new Text();

		Button button_normalMode = new Button("NORMAL");
		button_normalMode.setOnAction(e -> controller.onNormalModeButtonClicked(e));
		button_normalMode.getStyleClass().add("modeButton");
		GridPane.setHalignment(button_normalMode, HPos.CENTER);

		Button button_scoutMode = new Button(" SCOUT ");
		button_scoutMode.setOnAction(e -> controller.onScoutModeButtonClicked(e));
		button_scoutMode.getStyleClass().add("modeButton");
		GridPane.setHalignment(button_scoutMode, HPos.CENTER);

		Button button_shieldMode = new Button("SHIELD");
		button_shieldMode.setOnAction(e -> controller.onShieldModeButtonClicked(e));
		button_shieldMode.getStyleClass().add("modeButton");
		GridPane.setHalignment(button_shieldMode, HPos.CENTER);

		Button button_useSkill = new Button("USE SKILLZ");
		button_useSkill.setOnAction(e -> controller.onSkillButtonClicked(e));
		button_useSkill.getStyleClass().add("skillButton");

		PieceBox.add(label_piece, 0, 0, 1, 1);
		PieceBox.add(piece, 1, 0, 1, 1);
		PieceBox.add(label_mode, 0, 1, 1, 1);
		PieceBox.add(mode, 1, 1, 1, 1);
		PieceBox.add(label_power, 0, 2, 1, 1);
		PieceBox.add(power, 1, 2, 1, 1);
		PieceBox.add(button_normalMode, 0, 3, 1, 1);
		PieceBox.add(button_scoutMode, 1, 3, 1, 1);
		PieceBox.add(button_shieldMode, 2, 3, 1, 1);
		PieceBox.add(button_useSkill, 1, 4, 1, 1);

		getChildren().add(PieceBox);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (model.getLeague() == Game.getInstance().getCurrentTurn()) {
			Piece selected = (Piece) evt.getNewValue();
			if (selected != null) {
				piece.setText(selected.getTYPE().toString());
				mode.setText(selected.getMode().toString());
				power.setText(String.valueOf(selected.getPower()));
			}
		}
	}
}
