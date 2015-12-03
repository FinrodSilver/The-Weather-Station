package webb.richard;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import webb.richard.model.Freeze;
import webb.richard.model.Heat;
import webb.richard.model.Normal;
import webb.richard.model.Rain;
import webb.richard.model.Sun;
import webb.richard.model.WeatherData;
import webb.richard.model.WeatherDataController;
import webb.richard.model.WeatherObserver;

/**
 * Main GUI Controller for logic tying in the mainGUI.fxml File
 * 
 * @author Richard Webb
 *
 */
public class mainController {

	// Variables
	@FXML
	private Button Delete;
	@FXML
	private TextField tempText;
	@FXML
	private TextField pressureText;
	@FXML
	private TextField humidityText;
	@FXML
	private TextField windText;
	@FXML
	private TableView<WeatherData> dbTable;
	@FXML
	private TableColumn<WeatherData, Float> tempColumn;
	@FXML
	private TableColumn<WeatherData, Float> pressureColumn;
	@FXML
	private TableColumn<WeatherData, Float> humidityColumn;
	@FXML
	private TableColumn<WeatherData, Float> windColumn;
	@FXML
	private TextArea currentConditionsText;
	@FXML
	private TextField forecastText;
	@FXML
	private TextField heatText;
	@FXML
	private TextField windChillText;
	@FXML
	private ImageView percipitation;
	@FXML
	private ImageView airTemp;

	// set images
	Image hotImage = new Image("/webb/richard/Hot.png");
	Image coldImage = new Image("/webb/richard/Cold.png");
	Image MildImage = new Image("/webb/richard/Mild.png");
	Image sunnyImage = new Image("/webb/richard/Sunny.png");
	Image rainyImage = new Image("/webb/richard/RainyDay.png");

	private Main mainApp;
	private WeatherObserver observation = new WeatherObserver();

	/**
	 * Default Constructor
	 * 
	 */
	public mainController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		tempColumn.setCellValueFactory(cellData -> cellData.getValue().tempFloatProperty().asObject());
		pressureColumn.setCellValueFactory(cellData -> cellData.getValue().pressureFloatProperty().asObject());
		humidityColumn.setCellValueFactory(cellData -> cellData.getValue().humidityFloatProperty().asObject());
		windColumn.setCellValueFactory(cellData -> cellData.getValue().speedFloatProperty().asObject());

		dbTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> fillTextFields(newValue));

	}

	/**
	 * sets the dbTable with the Observable Field
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		dbTable.setItems(this.mainApp.getData());
	}

	/**
	 * Fills the Text Fields for Temp, Pressure, Wind Speed, and Humidity
	 * 
	 * @param data
	 */
	private void fillTextFields(WeatherData data) {

		if (data != null) {
			tempText.setText(data.getTemperature().toString());
			pressureText.setText(data.getPressure().toString());
			humidityText.setText(data.getHumidity().toString());
			windText.setText(data.getSpeed().toString());
		} else {
			tempText.setText("");
			pressureText.setText("");
			humidityText.setText("");
			windText.setText("");
		}
	}

	/**
	 * Called when the user clicks Delete
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void deleteData() throws SQLException {
		WeatherDataController controller = new WeatherDataController();
		int selectedIndex = dbTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			controller.query();
			ResultSet rs = controller.getRs();
			rs.absolute(selectedIndex + 1);
			int i = rs.getInt("ID");
			controller.delete(i);
			dbTable.getItems().clear();
			mainApp.refreshTable();
			currentConditionsText.setText("");
			forecastText.setText("");
			heatText.setText("");
			windChillText.setText("");
			rs.close();
		} else {
			// No items to delete
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Data Selected!");
			alert.setContentText("Please select weather to delete");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks Add
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void addData() throws SQLException {
		WeatherDataController controller = new WeatherDataController();
		try {
			controller.add(Float.parseFloat(tempText.getText()), Float.parseFloat(pressureText.getText()),
					Float.parseFloat(humidityText.getText()), Float.parseFloat(windText.getText()));
			dbTable.getItems().clear();
			mainApp.refreshTable();
			currentConditionsText.setText("");
			forecastText.setText("");
			heatText.setText("");
			windChillText.setText("");
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Wrong Data");
			alert.setHeaderText("Wrong Data or No Data!");
			alert.setContentText("Please Enter the Correct Data!");

			alert.showAndWait();
		}
	}

	/**
	 * Update the weather record in the Database
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void updateData() throws SQLException {
		WeatherDataController controller = new WeatherDataController();
		try {
			int selectedIndex = dbTable.getSelectionModel().getSelectedIndex();
			controller.query();
			ResultSet rs = controller.getRs();
			rs.absolute(selectedIndex + 1);
			int i = rs.getInt("ID");
			controller.update(i, Float.parseFloat(tempText.getText()), Float.parseFloat(pressureText.getText()),
					Float.parseFloat(humidityText.getText()), Float.parseFloat(windText.getText()));
			dbTable.getItems().clear();
			mainApp.refreshTable();
			currentConditionsText.setText("");
			forecastText.setText("");
			heatText.setText("");
			windChillText.setText("");
			rs.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Update Failed");
			alert.setHeaderText("Wrong Data or No Data!");
			alert.setContentText("Please Enter the Correct Data to Update!");

			alert.showAndWait();
		}
	}

	/**
	 * Get the conditions for the currently selected forecast, including
	 * WindChill, Heat Index, Forecast, and Conditions
	 * @throws SQLException 
	 */
	@FXML
	private void getConditions() throws SQLException {
		currentConditionsText.setText("");
		forecastText.setText("");
		heatText.setText("");
		windChillText.setText("");
		WeatherDataController controller = new WeatherDataController();
		try {
			Integer selectedIndex = dbTable.getSelectionModel().getSelectedIndex();
			controller.query();
			ResultSet rs = controller.getRs();
			rs.absolute(selectedIndex + 1);
			Integer i = rs.getInt("ID");
			// Create a WeatherData Object if one has not been made, then pull
			// the measurements
			if (observation.getWeatherData() == null) {
				WeatherData weather = new WeatherData(i, Float.parseFloat(tempText.getText()),
						Float.parseFloat(humidityText.getText()), Float.parseFloat(pressureText.getText()),
						Float.parseFloat(windText.getText()));
				observation.setWeatherData(weather);
				observation.initializeObservables();
				observation.getWeatherData().pullMeasurements(i, Float.parseFloat(tempText.getText()),
						Float.parseFloat(humidityText.getText()), Float.parseFloat(pressureText.getText()),
						Float.parseFloat(windText.getText()));
			} else {
				observation.getWeatherData().pullMeasurements(i, Float.parseFloat(tempText.getText()),
						Float.parseFloat(humidityText.getText()), Float.parseFloat(pressureText.getText()),
						Float.parseFloat(windText.getText()));
			}

			// Send Current conditions to the text box
			currentConditionsText.setText(observation.getCurrent().getCondition());
			currentConditionsText.appendText("\n" + observation.getStats().getStatistics());

			// Determine by pressure and humidity if it is raining or sunny,
			// change picture
			if (Float.parseFloat(pressureText.getText()) <= 25.0f
					&& Float.parseFloat(humidityText.getText()) >= 70.0f) {
				WeatherData rain = new Rain();
				currentConditionsText.appendText("\n" + rain.forecastPrediction());
				percipitation.setImage(rainyImage);
			} else {
				WeatherData sun = new Sun();
				currentConditionsText.appendText("\n" + sun.forecastPrediction());
				percipitation.setImage(sunnyImage);
			}

			// Determine if it is Hot, Cold, or Mild based on temp, change
			// picture
			if (Float.parseFloat(tempText.getText()) <= 40.0f) {
				WeatherData cold = new Freeze();
				currentConditionsText.appendText("\n" + cold.forecastPrediction());
				airTemp.setImage(coldImage);
			} else if (Float.parseFloat(tempText.getText()) < 80.0f && Float.parseFloat(tempText.getText()) > 40.0f) {
				WeatherData mild = new Normal();
				currentConditionsText.appendText("\n" + mild.forecastPrediction());
				airTemp.setImage(MildImage);
			} else {
				WeatherData hot = new Heat();
				currentConditionsText.appendText("\n" + hot.forecastPrediction());
				airTemp.setImage(hotImage);
			}

			forecastText.setText(observation.getForecast().getForecast());
			heatText.setText(observation.getHeatIndex().getHeatIndex());
			windChillText.setText(observation.getWindChillIndex().getWindChill());
			rs.close();
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Forecast Issues");
			alert.setHeaderText("Forecast Failed!");
			alert.setContentText("The Forecast Failed! Please Try Again!");
			alert.showAndWait();
		}
	}

}
