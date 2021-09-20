package Decorator;

import Factory.Notification;

public class TranslateDecorator implements Notification {

    private final Notification notification;
    private final User user;

    public TranslateDecorator(Notification notification, User user) {
        this.notification = notification;
        this.user = user;
    }


    @Override
    public void setMessage(String message) {
        notification.setMessage(message);
    }

    @Override
    public String getText() {
        setMessage(pickMessage());
        return String.format(notification.getText(),
                user.getName(),
                user.getWorkPlace()
        );

    }

    private String pickMessage() {
        switch (user.getLanguage()) {
            case ("Eng"):
                return Template.ENGLISH.getMessage();
            case ("Rus"):
                return Template.RUSSIAN.getMessage();
            case ("Cz"):
                return Template.CZECH.getMessage();
            default:
                return Template.ENGLISH.getMessage();
        }
    }
}