package webb.richard;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import webb.richard.model.WeatherData;
import webb.richard.model.WeatherDataController;

/**
 * Main Class for running the Weather Station
 * @author Richard Webb
 *
 */
public class Main extends Application {
	// Observable List
	private ObservableList<WeatherData> data = FXCollections.observableArrayList();

	private Stage primaryStage;
	private BorderPane root;

	/**
	 * Default Constructor Pull result sets from DB and fill the ArrayList()
	 * 
	 * @throws SQLException
	 */
	public Main() throws SQLException {
		
		refreshTable();
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("The Weather Station");
		
		initRootLayout();
		showMainGUI();
	}

	/**
	 * @return Weather data
	 */
	public ObservableList<WeatherData> getData() {
		return data;
	}


	/**
	 * Refresh the Weather Data Table
	 * @throws SQLException
	 */
	public void refreshTable() throws SQLException {
		
		WeatherDataController controller = new WeatherDataController();
		controller.query();
		ResultSet rs = controller.getRs();
		try {
			while (rs.next()) {
				data.add(new WeatherData(rs.getInt("ID"), rs.getFloat("temperature"), rs.getFloat("humidity"), rs.getFloat("pressure"),
						rs.getFloat("windspeed")));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		controller.close();
	}
	/**
	 * Show the root layout
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("rootLayer.fxml"));
			root = (BorderPane) loader.load();

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * Show the main GUI layout
	 */
	public void showMainGUI() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("mainGUI.fxml"));
			AnchorPane mainGUI = (AnchorPane) loader.load();

			root.setCenter(mainGUI);

			mainController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
