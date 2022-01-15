package net.seb.javafx.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.seb.javafx.EmailManager;
import net.seb.javafx.view.ColorTheme;
import net.seb.javafx.view.FontSize;
import net.seb.javafx.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {
    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void applyButtonAction() {
        viewFactory.setColorTheme(themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int) (fontSizePicker.getValue())]);
        viewFactory.updateStyles();
        //System.out.println(viewFactory.getColorTheme());
        //System.out.println(viewFactory.getFontSize());
    }

    @FXML
    void cancelButtonAction() {
        Stage stage = (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupThemePicker();
        setupFontSizePicker();
    }

    private void setupFontSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obs, oldValue, newValue) -> {
            fontSizePicker.setValue(newValue.intValue());
        });
    }

    private void setupThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}
