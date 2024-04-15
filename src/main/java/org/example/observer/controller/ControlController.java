package org.example.observer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class ControlController {
    @FXML
    private Slider slider;
    @FXML
    private Button triggerBtn;

    public Slider getSlider() {
        return slider;
    }

    public Button getTriggerBtn() {
        return triggerBtn;
    }

}
