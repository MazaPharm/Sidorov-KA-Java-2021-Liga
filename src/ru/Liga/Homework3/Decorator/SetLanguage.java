package ru.Liga.Homework3.Decorator;

import ru.Liga.Homework3.Template;
import ru.Liga.Homework3.factory.Notification;

public class SetLanguage extends TranslateDecorator {
    public SetLanguage(Notification notification, User user) {
        super(notification, user);
    }



}
