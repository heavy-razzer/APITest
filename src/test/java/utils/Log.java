package utils;

import objects.Colours;
import org.junit.Assert;

/*
Functions to print messages in log
 */
public class Log {

    public static void log(Object text, Object... args) {
        System.out.println(Colours.GREEN.getValue() + "[TEST]:  " + Colours.DEFAULT.getValue()
                + String.format(text.toString(), args));
    }

    public static void errLog(Object text, Object... args) {
        String msg = String.format(text.toString(), args);
        System.out.println(Colours.RED.getValue() + "[ERROR]: " + Colours.DEFAULT.getValue() + msg);
        Assert.fail(msg);
    }

    public static void sysLog(Object text, Object... args) {
        System.out.println(Colours.BLUE.getValue() + "[SYSTEM]: " + Colours.DEFAULT.getValue()
                + String.format(text.toString(), args));
    }

    public static void print(Object text, Colours colour) {
        System.out.println(colour.getValue() + text.toString() + Colours.DEFAULT.getValue());
    }
}
