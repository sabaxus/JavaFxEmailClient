package net.seb.javafx;

import javafx.scene.control.TreeItem;
import net.seb.javafx.controller.services.FetchFoldersService;
import net.seb.javafx.model.EmailAccount;
import net.seb.javafx.model.EmailTreeItem;

public class EmailManager {

    // Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}
