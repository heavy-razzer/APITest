package objects;

/*
Colours used for highlightning test on log
 */
public enum Colours {
    DEFAULT("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m"),
    PURPLE("\u001B[35m");

    private String colour;

    Colours(String color) {
        this.colour = color;
    }

    public String getValue() {
        return colour;
    }
}
