package ru.Liga.Homework3.factory;

import ru.Liga.Homework3.Decorator.User;

public interface NotificationFactory {
    public Notification getNotification(String language);
}
