package net.seb.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.seb.javafx.view.ViewFactory;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

//        Parent parent = FXMLLoader.load(getClass().getResource("view/mainWindow.fxml"));
//
//        Scene scene = new Scene(parent);
//        stage.setScene(scene);
//
//        stage.show();

        ViewFactory viewFactory = new ViewFactory(new EmailManager());
//        viewFactory.showOptionsWindow();
//        viewFactory.updateStyles();
        viewFactory.showLoginWindow();
    }
}
