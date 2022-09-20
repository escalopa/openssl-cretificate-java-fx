package source.opensslcretificatejavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import source.opensslcretificatejavafx.configuration.annotations.Component;
import source.opensslcretificatejavafx.utils.MainController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static source.opensslcretificatejavafx.MainApplication.stage;

@Component
public class SettingsController extends MainController implements Initializable {

    @FXML
    public ChoiceBox pkAlgorithm;
    @FXML
    public ChoiceBox pkLength;
    @FXML
    public Label outputPath;

    @FXML
    public void setOutputPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            settings.directory = file.getAbsolutePath() + "/";
            outputPath.setText(settings.directory);
        }
    }

    @FXML
    public void saveKeyProperties(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if (pkAlgorithm.equals(object))
            settings.algorithmName = (String) pkAlgorithm.getValue();
        else if (pkLength.equals(object))
            settings.algorithmLength = Integer.parseInt(pkLength.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputPath.setText(System.getProperty("user.home"));
    }

    @Override
    public boolean saveSettings() {
        if (areValuesIntegrated()) {
            settings.algorithmLength = Integer.parseInt(pkLength.getValue().toString());
            settings.algorithmName = pkAlgorithm.getValue().toString();
            return true;
        }
        return false;
    }

    @Override
    public boolean areValuesIntegrated() {
        if (pkLength.getValue() == null || pkAlgorithm.getValue() == null) {
            showError("Configuration Error", "Select PK properties");
            return false;
        }

        return isDirectoryValid();
    }
}
