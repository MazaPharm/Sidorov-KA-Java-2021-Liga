package ru.Liga.Homework3.Decorator;

import ru.Liga.Homework3.Template;

public class SetLanguage extends TranslateDecorator {
    public SetLanguage(GetMessage getMessage, String language, Template template) {
        super(getMessage, language,template);
    }
}
