package ru.Liga.Homework3;

import ru.Liga.Homework3.Decorator.CreateMessage;
import ru.Liga.Homework3.Decorator.GetMessage;
import ru.Liga.Homework3.Decorator.SetLanguage;
import ru.Liga.Homework3.Decorator.User;
import ru.Liga.Homework3.factory.MailNotificationFactory;
import ru.Liga.Homework3.factory.Notification;
import ru.Liga.Homework3.factory.NotificationFactory;

public class Main {
    private static User user;
    public static void main(String[] args) {
        user = new User("Kirill","Liga", "Rus");//Доступные языки "Cz","Eng","Rus"
        //Если будет указан другой язык, которого нет в списке, то выведется по умолчанию
        // на Английском
        MessagePick.mapInitialization();
        NotificationFactory factory = new MailNotificationFactory();
        reciveMessage(factory.getNotification(user));
    }

    public static void reciveMessage(Notification notification){
        GetMessage message = new CreateMessage(notification);
        //Здесь выбираем тип сообщения Template.WELCOME, Template.FAREWELL, Template.TASKS
        GetMessage languageSet= new SetLanguage(message,user.getLanguage(), Template.WELCOME);
        System.out.println(languageSet.createMessage());
    }
}
