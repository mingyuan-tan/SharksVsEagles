package view;

import controller.MenuController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewGameView extends GridPane {
	private static final Font fONT_TITLE = Font.font("silom", 30);
	private static final Font fONT_LABEL = Font.font("silom", 18);
	private static final Font fONT_INPUT = Font.font("silom", 20);

	private final MenuController controller;
	private TextField textField_p1;
	private TextField textField_p2;

	public NewGameView(MenuController controller) {
		this.controller = controller;
		buildLayout();
		buildComponents();
	}

	private void buildLayout() {
		setPadding(new Insets(20, 50, 20, 50));
		setAlignment(Pos.TOP_CENTER);
		setHgap(40);
		setVgap(40);
	}

	private void buildComponents() {

		Text title = new Text("New Game");
		title.setFont(fONT_TITLE);
		setHalignment(title, HPos.CENTER);

		Label label_p1 = new Label("Eagle Player :");
		label_p1.setFont(fONT_LABEL);
		Label label_p2 = new Label("Shark Player :");
		label_p2.setFont(fONT_LABEL);
		Label label_size = new Label("Board Size :");
		label_size.setFont(fONT_LABEL);
		Label label_level = new Label("With Obstacles :");
		label_level.setFont(fONT_LABEL);

		textField_p1 = new TextField("Eagle-Bearer");
		textField_p1.setFont(fONT_INPUT);
		textField_p1.setStyle("-fx-text-inner-color: DARKGREY;");
		textField_p1.setAlignment(Pos.CENTER);

		textField_p2 = new TextField("Sharknado");
		textField_p2.setFont(fONT_INPUT);
		textField_p2.setStyle("-fx-text-inner-color: DARKGREY;");
		textField_p2.setAlignment(Pos.CENTER);

		ToggleGroup group_size = new ToggleGroup();
		RadioButton rb_size1 = new RadioButton("10 * 10");
		rb_size1.setFont(fONT_INPUT);
		rb_size1.setUserData(10);
		rb_size1.setToggleGroup(group_size);
		rb_size1.setSelected(true);
		RadioButton rb_size2 = new RadioButton("12 * 12");
		rb_size2.setFont(fONT_INPUT);
		rb_size2.setUserData(12);
		rb_size2.setToggleGroup(group_size);

		ToggleGroup group_obstacle = new ToggleGroup();
		RadioButton rb_obstacle1 = new RadioButton("No");
		rb_obstacle1.setFont(fONT_INPUT);
		rb_obstacle1.setUserData(false);
		rb_obstacle1.setToggleGroup(group_obstacle);
		rb_obstacle1.setSelected(true);
		RadioButton rb_obstacle2 = new RadioButton("Yes");
		rb_obstacle2.setFont(fONT_INPUT);
		rb_obstacle2.setUserData(true);
		rb_obstacle2.setToggleGroup(group_obstacle);

		Button button_go = new Button("GO");
		button_go.setFont(fONT_INPUT);
		button_go.setMinWidth(100);
		setHalignment(button_go, HPos.CENTER);
		button_go.setOnMouseClicked(e -> controller.onClickGoButton(textField_p1.getText(), textField_p2.getText(),
				group_size.getSelectedToggle().getUserData(), group_obstacle.getSelectedToggle().getUserData()));

		add(title, 0, 0, 3, 1);
		add(label_p1, 0, 1, 1, 1);
		add(textField_p1, 1, 1, 2, 1);
		add(label_p2, 0, 2, 1, 1);
		add(textField_p2, 1, 2, 2, 1);
		addRow(3, label_size, rb_size1, rb_size2);
		addRow(4, label_level, rb_obstacle1, rb_obstacle2);
		add(button_go, 0, 5, 3, 1);
	}
}
