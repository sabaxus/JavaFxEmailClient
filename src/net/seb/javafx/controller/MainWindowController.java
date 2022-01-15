package net.seb.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import net.seb.javafx.EmailManager;
import net.seb.javafx.model.EmailMessage;
import net.seb.javafx.model.EmailTreeItem;
import net.seb.javafx.view.ViewFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    @FXML
    private WebView emailWebView;

    @FXML
    private TableView<EmailMessage> emailsTableView;

    @FXML
    private TableColumn<EmailMessage, String> senderCol;

    @FXML
    private TableColumn<EmailMessage, String> subjectCol;

    @FXML
    private TableColumn<EmailMessage, String> recipientCol;

    @FXML
    private TableColumn<EmailMessage, Integer> sizeCol;

    @FXML
    private TableColumn<EmailMessage, Date> dateCol;

    @FXML
    private TreeView<String> emailsTreeView;

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void optionsAction() {
        viewFactory.showOptionsWindow();
    }

    @FXML
    void addAccountAction() {
        viewFactory.showLoginWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEmailsTreeView();
        setupEmailsTableView();
        setupFolderSelection();
    }

    private void setupFolderSelection() {
        emailsTreeView.setOnMouseClicked(event -> {
            EmailTreeItem<String> item = (EmailTreeItem<String>) emailsTreeView.getSelectionModel().getSelectedItem();
            if (item != null) {
                emailsTableView.setItems(item.getEmailMessages());
            }
        });
    }

    private void setupEmailsTableView() {
        senderCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        recipientCol.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setupEmailsTreeView() {
        emailsTreeView.setRoot(emailManager.getFoldersRoot());
        emailsTreeView.setShowRoot(false);
    }
}
