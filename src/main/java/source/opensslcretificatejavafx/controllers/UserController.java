package source.opensslcretificatejavafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import source.opensslcretificatejavafx.configuration.annotations.Component;
import source.opensslcretificatejavafx.utils.MainController;

import java.io.File;
import java.io.IOException;

import static source.opensslcretificatejavafx.MainApplication.stage;

@Component
public class UserController extends MainController {

    @FXML
    public TextField countryCode;
    @FXML
    public TextField region;
    @FXML
    public TextField city;
    @FXML
    public TextField organization;
    @FXML
    public TextField department;
    @FXML
    public TextField username;
    @FXML
    public TextField email;

    @FXML
    public void selectPrivateKey() {
        File file = selectFileFromDirectory("Private Key (*.key)", "*.key");
        if (file != null)
            settings.user.adminKeyFile = file.getAbsolutePath();
    }

    @FXML
    public void selectCertificate() {
        File file = selectFileFromDirectory("Certificate (*.crt)", "*.crt");
        if (file != null)
            settings.user.adminCertificateFile = file.getAbsolutePath();
    }

    private File selectFileFromDirectory(String extensionTitle, String extensionFormat) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extensionTitle, extensionFormat));
        return fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void createCertificate() {
        try {
            if (saveSettings()) {
                executeCommand.createPrivateKey(settings.user.username);
                executeCommand.createUserOrganizationCertificate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveSettings() {
        if (areValuesIntegrated()) {
            //User's certificate data
            settings.user.countryCode = countryCode.getText();
            settings.user.region = region.getText();
            settings.user.city = city.getText();
            settings.user.organization = organization.getText();
            settings.user.department = department.getText();
            settings.user.username = username.getText();
            settings.user.email = email.getText();
            return true;
        }
        return false;
    }

    @Override
    public boolean areValuesIntegrated() {
        if (countryCode.getText().isBlank() ||
                region.getText().isBlank() ||
                city.getText().isBlank() ||
                organization.getText().isBlank() ||
                department.getText().isBlank() ||
                username.getText().isBlank() ||
                email.getText().isBlank()) {
            showError("Create Certificate Error", "Fill the User's certificate data");
            return false;
        }

        return isCountryCodeValid(countryCode) && isAdminCertificateDataValid();
    }

}
