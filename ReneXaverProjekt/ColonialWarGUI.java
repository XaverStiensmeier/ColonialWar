import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

public class ColonialWarGUI extends Application implements EventHandler<ActionEvent> {

	private Button zButton;
	private Group zMyGroup = new Group();
	private Group zMapGroup = new Group();
	private Stage zWindow;
	private Scene zGameScene;
	// behelfs
	private Control zMyControl = new Control(this);

	// funktioniert noch nicht als Client sondern simuliert diesen. Wird später
	// mit entsprechender Funktionalität ausgestattet.
	// ColonialWarClient myClient = new ColonialWarClient();
	public static void main(String[] args) {
		// behelfs
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Title of zWindow");
		zButton = new Button();
		zButton.setText("Hey baby; LineSetUp1");

		// This class will handle the zButton events
		zButton.setOnAction(this);

		StackPane layout = new StackPane();
		layout.getChildren().add(zButton);
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		zWindow = primaryStage;

	}

	// When zButton is clicked, handle() gets called
	// zButton click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == zButton) {
			zMyControl.createMap(10, 10);
			zMyControl.lineSetUp();
			// createRectangle(5, 5);
			zMyControl.createMapView();
			fullUpdate();
		}

	}

	public void createGrass(int pValueX, int pValueY) {
		// erstelle ein Feld
		Rectangle lRect = new Rectangle(pValueX, pValueY, 30, 30);
		lRect.setFill(Color.TRANSPARENT);
		lRect.setStroke(Color.BLACK);
		zMapGroup.getChildren().add(lRect);
	}

	public void fullUpdate() {
		zMyGroup.getChildren().addAll(zMapGroup);
		zGameScene = new Scene(zMyGroup, 400, 400);
		zGameScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode().isDigitKey())
				zMyControl.getUserInput(key.getCode());
			else {
				switch (key.getCode()) {
				case ESCAPE:
					System.exit(0);
					Platform.exit();
					break;
				default:
					System.out.println("Dont be that kid");
				}
			}
		});
		zWindow.setScene(zGameScene);
		zMyControl.getNextUnit();
	}
	public void reset() {
		zMyGroup.getChildren().clear();
	}
	public void addGrid() {
		zMyGroup.getChildren().addAll(zMapGroup);
	}
	public void createUnitUpdate(int pValueX, int pValueY) {
		Rectangle lRect = new Rectangle(pValueX * 30 + 17.5, pValueY  * 30 + 17.5, 15, 15);
		lRect.setFill(Color.BLACK);
		lRect.setStroke(Color.BLACK);
		zMyGroup.getChildren().add(lRect);
	}
}
