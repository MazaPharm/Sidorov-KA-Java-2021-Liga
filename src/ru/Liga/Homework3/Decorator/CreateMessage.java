package ru.Liga.Homework3.Decorator;

import ru.Liga.Homework3.factory.Notification;

public class CreateMessage implements GetMessage {

private Notification notification;

    public CreateMessage(Notification notification){
        this.notification=notification;
    }

    @Override
    public String createMessage() {
        return notification.getText();
    }
}
