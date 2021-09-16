package ru.Liga.Homework3;

import ru.Liga.Homework3.Decorator.SetLanguage;
import ru.Liga.Homework3.Decorator.User;
import ru.Liga.Homework3.factory.MailNotificationFactory;
import ru.Liga.Homework3.factory.Notification;
import ru.Liga.Homework3.factory.NotificationFactory;

public class Main {
    private static User user;
    public static void main(String[] args) {
        user = new User("Kirill","Liga", "Eng");//Доступные языки "Cz","Eng","Rus"
        //Если будет указан другой язык, которого нет в списке, то выведется по умолчанию
        // на Английском
        NotificationFactory factory = new MailNotificationFactory();
        reciveMessage(factory.getNotification(user.getLanguage()));
    }

    public static void reciveMessage(Notification notification){
        Notification languageSet= new SetLanguage(notification,user);
        System.out.println(languageSet.getText());
    }
}
