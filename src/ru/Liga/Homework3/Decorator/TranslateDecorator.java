package ru.Liga.Homework3.Decorator;

import ru.Liga.Homework3.factory.Notification;

public class TranslateDecorator implements Notification {

    private  Notification notification;
    private User user;

    public TranslateDecorator(Notification notification, User user) {
        this.notification = notification;
        this.user=user;
    }


    @Override
    public String getText() {
        return String.format(notification.getText(),
                user.getName(),
                user.getWorkPlace()
        );

    }
}
