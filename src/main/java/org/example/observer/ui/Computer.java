package org.example.observer.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.observer.enums.ScreenStatus;

import java.util.Objects;

public class Computer extends ImageView implements ISubscriber{
    private final Image turnedOff;
    private final Image turnedOn;
    public Computer(ScreenStatus status) {
        this.turnedOn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("computer1.png")));
        this.turnedOff = new Image(Objects.requireNonNull(getClass().getResourceAsStream("computer2.png")));

        if (status == ScreenStatus.ON) {
            this.setImage(this.turnedOn);
        } else {
            this.setImage(this.turnedOff);
        }

        this.setFitHeight(54);
        this.setFitWidth(54);
    }

    @Override
    public void update() {
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> {this.setImage(this.turnedOn);});
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(0.3), e -> {this.setImage(this.turnedOff);});
        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
    }
}
