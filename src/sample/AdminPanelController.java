package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    AnchorPane editPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editPane.setDisable(true);
    }
}
