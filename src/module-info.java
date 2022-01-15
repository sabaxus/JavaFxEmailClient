module JavaFxEmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;

    opens net.seb.javafx;
    opens net.seb.javafx.view;
    opens net.seb.javafx.controller;
    opens net.seb.javafx.model;
}