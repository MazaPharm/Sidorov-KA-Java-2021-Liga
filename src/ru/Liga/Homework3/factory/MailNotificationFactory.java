package ru.Liga.Homework3.factory;

import ru.Liga.Homework3.Decorator.User;

public class MailNotificationFactory implements NotificationFactory {
    @Override
    public Notification getNotification(User user) {
        return new MailNotification(user);
    }
}
