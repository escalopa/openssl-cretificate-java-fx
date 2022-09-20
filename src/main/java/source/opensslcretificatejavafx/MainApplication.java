package source.opensslcretificatejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.opensslcretificatejavafx.configuration.Context;

import java.net.URL;

public class MainApplication extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        //Load context
        new Context(MainApplication.class.getPackageName());

        //Set project stage
        MainApplication.stage = stage;

        //Load FXML file
        //Path
        URL url = MainApplication.class.getResource("/fxml/interface.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        //Load JavaFX Frame
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Certificate Application");
        stage.setScene(scene);
        stage.show();
    }
}
