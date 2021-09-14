package ru.Liga.Homework3;

import java.util.HashMap;
import java.util.Map;

public class MessagePick {

    private static Map<String, String> welcome;
    private static Map<String, String> tasks;
    private static Map<String,String> farewell;

    public static void mapInitialization(){
        welcome=new HashMap<>();
        welcome.put("Rus", "Привет, %s! Как твои дела в %s?");
        welcome.put("Eng","Hi %s! How is going on %s?");
        welcome.put("Cz","Ahoj, %se! Jak je tvoje prace v %s?");

        tasks=new HashMap<>();
        tasks.put("Rus", "Это твои задачи, %s. %s верит, что ты справишься");
        tasks.put("Eng","%s there is your tasks. %s believes that you will pass it!");
        tasks.put("Cz", "%se, to je tvoje ukoly. %s veri ze udelas to uspesne!");

        farewell=new HashMap<>();
        farewell.put("Rus","До свидания, %s. %s ждет тебя завтра снова!");
        farewell.put("Eng","Goodbye %s. %s wants to see you tomorrow again!");
        farewell.put("Cz","Cao %s. %s zitra uvide tebe znovu!");
    }

    public  Map<String,String> getWelcome(){return welcome;}
    public  Map<String,String> getTasks(){return tasks;}
    public  Map<String,String> getFarewell(){return farewell;}

}
