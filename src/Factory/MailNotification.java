package Factory;

public class MailNotification implements Notification {

    private String message;

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getText() {
        return message;
    }
}