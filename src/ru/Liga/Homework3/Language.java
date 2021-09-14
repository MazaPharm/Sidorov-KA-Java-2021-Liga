package ru.Liga.Homework3;

import java.util.Map;

public class Language {
    private static String language;
    private static Map<String, String> m;

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMap(Map<String, String> m) {
        this.m = m;
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, String> getMap() {
        return m;
    }


//    public Language(String language){
//        this.language=language;
//
//    }
//    public String getLanguage(){return language;}
}
