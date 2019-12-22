package test;

import managers.FileManager;
import objects.Colours;
import org.json.JSONObject;
import org.junit.Test;

import static managers.HttpManager.getResponse;
import static managers.JsonManager.compareJSons;

/*
Main Assignment test:
- Process links from two files, one after another
- Compare JSONs for each pair
- Display result: JSONS equal or not
 */
public class linkSetTest {

    @Test
    public void executeCommandsFormFiles() {

        // Define name of files, located in '/files' directory
        String fileOneName = "file1.txt";
        String fileTwoName = "file2.txt";

        System.out.println("Run links set from '"
                + Colours.PURPLE.getValue() + fileOneName + Colours.DEFAULT.getValue()
                + "' and '"
                + Colours.PURPLE.getValue() + fileTwoName + Colours.DEFAULT.getValue()
                + "' and compare JSON results");

        // Load file reader
        FileManager file1 = new FileManager(fileOneName);
        FileManager file2 = new FileManager(fileTwoName);

        String stringFromFile1;
        String stringFromFile2;

        // Until end of file is not reached
        while (true) {

            // Try to read one string from each file
            stringFromFile1 = file1.getNextLine();
            stringFromFile2 = file2.getNextLine();

            // Check if not EOF
            if (stringFromFile1 == null) {
                System.out.println("End of file '" + fileOneName + "'");
                break;
            }

            if (stringFromFile2 == null) {
                System.out.println("End of file '" + fileTwoName + "'");
                break;
            }

            // Execute http commands
            JSONObject js1 = getResponse(stringFromFile1);
            JSONObject js2 = getResponse(stringFromFile2);

            // In case you would like to display responses...
            /*
            if (js1 != null) {
                System.out.println(js1.toString() + "\n");
            } else {
                System.out.println("Link 1 returned no JSON\n");
            }
            if (js2 != null) {
                System.out.println(js2.toString() + "\n");
            } else {
                System.out.println("Link 2 returned no JSON\n");
            }

             */

            // Compare responses
            String messageTemplate = Colours.CYAN.getValue() + stringFromFile1 + " %s " + Colours.CYAN.getValue() + stringFromFile2 + Colours.DEFAULT.getValue();
            if (compareJSons(js1, js2)) {
                System.out.println(String.format(messageTemplate, Colours.GREEN.getValue() + "equals"));
            } else {
                System.out.println(String.format(messageTemplate, Colours.RED.getValue() + "not equals"));
            }
        }
    }
}
