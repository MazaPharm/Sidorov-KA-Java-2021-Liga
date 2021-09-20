package Decorator;

public class User {
    private String name;
    private String workPlace;
    private String language;

    public User(String name, String workPlace, String language){
        this.name=name;
        this.workPlace=workPlace;
        this.language=language;
    }

    public String getLanguage(){return language;}

    public String getName(){
        return name;
    }
    public String getWorkPlace(){
        return workPlace;
    }
}