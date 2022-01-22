package net.seb.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.seb.javafx.controller.persistence.PersistenceAccess;
import net.seb.javafx.controller.persistence.ValidAccount;
import net.seb.javafx.controller.services.LoginService;
import net.seb.javafx.model.EmailAccount;
import net.seb.javafx.view.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application {

    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private EmailManager emailManager = new EmailManager();

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

        ViewFactory viewFactory = new ViewFactory(emailManager);
//        viewFactory.showOptionsWindow();
//        viewFactory.updateStyles();

        List<ValidAccount> validAccounts = persistenceAccess.loadFromPersistence();
        if (validAccounts.size() > 0) {
            viewFactory.showMainWindow();
            for (ValidAccount validAccount : validAccounts) {
                EmailAccount emailAccount = new EmailAccount(validAccount.getAddress(), validAccount.getPassword());
                LoginService loginService = new LoginService(emailAccount, emailManager);
                loginService.start();
            }
        } else {
            viewFactory.showLoginWindow();
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println(System.getProperty("user.home"));
        List<ValidAccount> validAccounts = new ArrayList<>();
        for (EmailAccount emailAccount : emailManager.getEmailAccounts()) {
            validAccounts.add(new ValidAccount(emailAccount.getAddress(), emailAccount.getPassword()));
        }
        persistenceAccess.saveToPersistence(validAccounts);
    }
}
