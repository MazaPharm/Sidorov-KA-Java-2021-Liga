package Factory;

public class MailNotificationFactory implements NotificationFactory {
    @Override
    public Notification getNotification() {
        return new MailNotification();
    }
}