package org.example.observer.ui;

public interface IPublisher {

    void addSubscriber(Computer subscriber);

    void publish();
}
