package org.example.observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.observer.controller.ControlController;
import org.example.observer.enums.ScreenStatus;
import org.example.observer.ui.CircularPane;
import org.example.observer.ui.Computer;

import java.io.IOException;

public class JavaFxObservableExample extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader controlViewFxml = new FXMLLoader((JavaFxObservableExample.class.getResource("control-view.fxml")));
        VBox rightPane = controlViewFxml.load();

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(40);
        CircularPane circularPane = new CircularPane();
        circularPane.setPrefWidth(120);

        for (int i = 0; i < 2; i++) {
            circularPane.getChildren().add(new Computer(ScreenStatus.OFF));
        }

        ControlController controller = controlViewFxml.getController();

        Slider slider = controller.getSlider();
        slider.setMax(10);
        slider.setMin(2);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(1);

        Button publishBtn = controller.getTriggerBtn();
        publishBtn.setOnAction(e -> {
            circularPane.publish();
        });

        slider.valueProperty().addListener(o -> {
            circularPane.addChildrenUntilReach((int) slider.getValue());
        });

        root.getChildren().add(circularPane);
        root.getChildren().add(rightPane);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}