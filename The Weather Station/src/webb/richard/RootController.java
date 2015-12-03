package webb.richard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller for the Root GUI Menu
 * 
 * @author Richard Webb
 *
 */
public class RootController {

	/**
	 * Exit the Program
	 */
	@FXML
	private void closeProgram() {
		Platform.exit();
	}

	/**
	 * The About Button on the Menu Bar
	 */
	@FXML
	private void helpAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText("The Weather Station , A school Project " + "\n by Richard Webb " + "\n 11/2015");

		alert.showAndWait();

	}
}
