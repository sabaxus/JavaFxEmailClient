package net.seb.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import net.seb.javafx.EmailManager;
import net.seb.javafx.controller.services.MessageRendererService;
import net.seb.javafx.model.EmailMessage;
import net.seb.javafx.view.ViewFactory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsController extends BaseController implements Initializable {

    private String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "\\Downloads\\";

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    @FXML
    private Label attachmentLabel;

    @FXML
    private HBox hBoxDownloads;

    @FXML
    private WebView webView;

    public EmailDetailsController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EmailMessage emailMessage = emailManager.getSelectedMessage();
        subjectLabel.setText(emailMessage.getSubject());
        senderLabel.setText(emailMessage.getSender());

        loadAttachments(emailMessage);

        MessageRendererService messageRendererService = new MessageRendererService(webView.getEngine());
        messageRendererService.setEmailMessage(emailMessage);
        messageRendererService.restart();
    }

    private void loadAttachments(EmailMessage emailMessage) {
        if (emailMessage.hasAttachments()) {
            for (MimeBodyPart mimeBodyPart : emailMessage.getAttachmentList()) {
                Button button = null;
                try {
                    button = new Button(mimeBodyPart.getFileName());
                    hBoxDownloads.getChildren().add(button);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            attachmentLabel.setText("");
        }
    }
}
