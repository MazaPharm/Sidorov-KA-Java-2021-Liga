package Decorator;

import Factory.Notification;

public class SetLanguage extends TranslateDecorator {
    public SetLanguage(Notification notification, User user) {
        super(notification, user);
    }



}