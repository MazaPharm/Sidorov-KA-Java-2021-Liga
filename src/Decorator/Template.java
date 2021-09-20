package Decorator;

public enum Template {
    ENGLISH(
            "Hi %s! How is going on %s?"
    ),
    RUSSIAN("Привет, %s! Как твои дела в %s?"

    ),
    CZECH("Ahoj, %se! Jak je tvoje prace v %s?");

    private String message;

    Template(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
