package source.opensslcretificatejavafx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import source.opensslcretificatejavafx.configuration.annotations.Autowire;
import source.opensslcretificatejavafx.configuration.annotations.Component;
import source.opensslcretificatejavafx.services.ExecuteCommand;
import source.opensslcretificatejavafx.services.Settings;


import java.io.File;

@Component
public class MainController {

    @Autowire
    public static ExecuteCommand executeCommand;
    @Autowire
    public static Settings settings;

    public boolean areValuesIntegrated() {
        return true;
    }

    public boolean saveSettings() {
        return true;
    }

    public void showError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public boolean isDirectoryValid() {
        File file = new File(settings.directory);
        if (!(file.exists() || file.isDirectory())) {
            showError("Configuration Error", "Output Path Does Not Exists, Please Select Valid Output Path");
            return false;
        }
        return true;
    }

    public boolean isCountryCodeValid(TextField textField) {
        if (textField.getText().length() != 2) {
            showError("Input Error", "Country code must be 2 Letters only");
            return false;
        }
        return true;
    }

    public boolean isAdminCertificateDataValid() {
        File file1, file2;
        file1 = new File(settings.user.adminKeyFile);
        file2 = new File(settings.user.adminCertificateFile);
        if (!(file1.exists() || file1.isDirectory()) || !(file2.exists() || file2.isDirectory())) {
            showError("Admin's Certificate Error", "Admin's PK or Certificate are missing," +
                    " please select valid values");
            return false;
        }
        return true;
    }
}
