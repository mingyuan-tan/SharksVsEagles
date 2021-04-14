package view;

import controller.MenuController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SharkVsEagleApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		MenuController menu = new MenuController();
		Parent main = menu.getView();
		Scene scene = new Scene(main);
		primaryStage.setTitle("Shark vs Eagle");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
