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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ColonialWarGUI extends Application implements EventHandler<ActionEvent> {

	private Button zButton;
	private Group zUnitGroup = new Group();
	private Group zMapGroup = new Group();
	private Stage zWindow;
	private Scene zGameScene;
	private int zSize;
	// behelfs
	private Control zMyControl = new Control(this);

	// Einzige auf gabe ist die Visuelle Darstellung der physischen Objekte!
	// Wird durch den ColonialWarClient angesprochen ~
	// ColonialWarClient myClient = new ColonialWarClient();
	public static void main(String[] args) {
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
			zSize = 10;
			zMyControl.createMap(zSize, zSize);
			zMyControl.lineSetUp();
			zMyControl.createMapView();
			fullUpdate();
		}

	}

	public void createGrass(int pValueX, int pValueY) {
		// erstelle ein Gras Feld 0 und f�gt es der zMapGroup (Background
		// Group);
		Rectangle lRect = new Rectangle(pValueX, pValueY, 30, 30);
		lRect.setFill(Color.TRANSPARENT);
		lRect.setStroke(Color.BLACK);
		zMapGroup.getChildren().add(lRect);
	}

	public void fullUpdate() {
		// setUp GUI
		// zMapGroup := Background/Map GUI
		// zMyGroup := Die Sicht der Units
		Group zRoot = new Group(zMapGroup, zUnitGroup);
		zGameScene = new Scene(zRoot, 400, 400);
		// initialise KeyEvent
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
		// set Scene
		zWindow.setScene(zGameScene);
	}

	public void reset() {
		// cleare all Unit pics;
		zUnitGroup.getChildren().clear();
	}

	public void createPartialUpdate(int pValueX, int pValueY) {
		// erstellePartialPicture (sp�ter ben�tigen wir ein ImageVault und
		// m�ssen das so zuordnen);
		Rectangle lRect = new Rectangle(pValueX * 30 + 17.5, pValueY * 30 + 17.5, 15, 15);
		lRect.setFill(Color.BLACK);
		lRect.setStroke(Color.BLACK);
		zUnitGroup.getChildren().add(lRect);
	}
}
