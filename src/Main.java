import Decorator.SetLanguage;
import Decorator.User;
import Factory.MailNotificationFactory;
import Factory.Notification;
import Factory.NotificationFactory;

public class Main {
    private static User user;
    public static void main(String[] args) {
        user = new User("Kirill","Liga", "Eng");//Доступные языки "Cz","Eng","Rus"
        //Если будет указан другой язык, которого нет в списке, то выведется по умолчанию
        // на Английском
        NotificationFactory factory = new MailNotificationFactory();
        reciveMessage(factory.getNotification());
    }

    public static void reciveMessage(Notification notification){
        Notification languageSet= new SetLanguage(notification,user);
        System.out.println(languageSet.getText());
    }
}