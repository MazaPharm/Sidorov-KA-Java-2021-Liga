package ru.Liga.Homework3.factory;

import ru.Liga.Homework3.Decorator.User;
import ru.Liga.Homework3.Language;

import java.util.HashMap;
import java.util.Map;

public class MailNotification implements Notification {

    private User user;
    private Map<String, String> m;
    public MailNotification(User user){
        this.user=user;
    }


    @Override
    public String getText() {
        Map<String, String>m=Language.m;
        String language=Language.language;
        if(m.containsKey(language))
            return String.format(
                    m.get(language),
                    user.getName(),
                    user.getWorkPlace()

            );
        return String.format(
                m.get("Eng"),
                user.getName(),
                user.getWorkPlace()
        );
    }
}
