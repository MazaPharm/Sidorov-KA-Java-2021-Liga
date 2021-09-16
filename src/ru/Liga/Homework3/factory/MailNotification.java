package ru.Liga.Homework3.factory;

import ru.Liga.Homework3.Template;

public class MailNotification implements Notification {

    private static String language;

    public MailNotification(String language){
        this.language=language;
    }

    private static String pickMessage(){
        switch (language){
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


    @Override
    public String getText() {
       return pickMessage();
    }
}
