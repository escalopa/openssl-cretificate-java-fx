module source.opensslcretificatejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports source.opensslcretificatejavafx.services;
    exports source.opensslcretificatejavafx.models;
    exports source.opensslcretificatejavafx.controllers;
    opens source.opensslcretificatejavafx to javafx.fxml;
    exports source.opensslcretificatejavafx;
}
