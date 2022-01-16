package net.seb.javafx;

import javafx.scene.control.TreeItem;
import net.seb.javafx.controller.services.FetchFoldersService;
import net.seb.javafx.controller.services.FolderUpdaterService;
import net.seb.javafx.model.EmailAccount;
import net.seb.javafx.model.EmailTreeItem;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private FolderUpdaterService folderUpdaterService;

    // Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    private List<Folder> folderList = new ArrayList<>();

    public EmailManager() {
        folderUpdaterService = new FolderUpdaterService(folderList);
        folderUpdaterService.start();
    }

    public List<Folder> getFolderList() {
        return this.folderList;
    }

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, folderList);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}
