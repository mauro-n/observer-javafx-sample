package org.example.observer.ui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.observer.enums.ScreenStatus;

public class CircularPane extends Pane implements IPublisher {
    public CircularPane() {
        getChildren().add(new Computer(ScreenStatus.ON));
    }
    @Override
    protected void layoutChildren() {
        final int radius = 50;
        final double increment = 360 / (getChildren().size() - 1);
        double degrees = 0;

        // Calculate center coordinates
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Center the first node
        if (!getChildren().isEmpty()) {
            Node centerNode = getChildren().get(0); // Center node is 0, as its added on constructor
            layoutInArea(centerNode, centerX - centerNode.getBoundsInLocal().getWidth() / 2,
                    centerY - centerNode.getBoundsInLocal().getHeight() / 2, centerNode.getBoundsInLocal().getWidth(),
                    centerNode.getBoundsInLocal().getHeight(), 0.0, HPos.CENTER, VPos.CENTER);
        }

        // Lay children nodes
        for (int i = 1; i < getChildren().size(); i++) {
            Computer node = (Computer) getChildren().get(i);
            double x = radius * Math.cos(Math.toRadians(degrees)) + getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(degrees)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += increment;
        }
    }

    public void addChildrenUntilReach(int number) {
        while (getChildren().size() != number + 1) {
            if (getChildren().size() > number) {
                getChildren().remove(getChildren().size() -1);
            } else {
                getChildren().add(new Computer(ScreenStatus.OFF));
            }
        }
    }

    @Override
    public void addSubscriber(Computer subscriber) {
        getChildren().add(subscriber);
    }

    @Override
    public void publish() {
        for (int i = 1; i < getChildren().size(); i++) {
            ISubscriber subscriber = (ISubscriber) getChildren().get(i);
            subscriber.update();
        }
    }
}
