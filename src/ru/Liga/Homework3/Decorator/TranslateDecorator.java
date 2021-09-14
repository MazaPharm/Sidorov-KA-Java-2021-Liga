package ru.Liga.Homework3.Decorator;

import ru.Liga.Homework3.Language;
import ru.Liga.Homework3.MessagePick;
import ru.Liga.Homework3.Template;

import java.util.Map;

public class TranslateDecorator implements GetMessage {

    private GetMessage getMessage;
    private String language;
    private Template template;

    public TranslateDecorator(GetMessage getMessage, String language,Template template){
        this.getMessage=getMessage;
        this.language=language;
        this.template=template;
    }

    @Override
    public String createMessage() {
        Language.language=language;
        MessagePick.mapInitialization();
        Language.m=pickMap();
        return getMessage.createMessage();
    }

    private Map<String,String> pickMap(){
        MessagePick messagePick = new MessagePick();
        switch (template){
            case WELCOME:
                return messagePick.getWelcome();
            case TASKS:
                return messagePick.getTasks();
            case FAREWELL:
                return messagePick.getFarewell();
            default:
                return messagePick.getWelcome();
        }
    }
}
