package ru.Liga.Homework3.factory;

import ru.Liga.Homework3.Decorator.User;

public class MailNotificationFactory implements NotificationFactory {
    @Override
    public Notification getNotification(String language) {
        return new MailNotification(language);
    }
}
