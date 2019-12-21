import managers.FileManager;
import objects.Colours;
import org.json.JSONObject;
import org.junit.Test;

import static managers.HttpManager.getResponse;
import static managers.JsonManager.compareJSons;

public class myTest {

    @Test
    public void test() {

        FileManager file1 = new FileManager("file1full.txt");
        FileManager file2 = new FileManager("file2full.txt");

        String stringFromFile1;
        String stringFromFile2;

        while (true) {

            stringFromFile1 = file1.getNextLine();
            stringFromFile2 = file2.getNextLine();

            if (stringFromFile1 == null || stringFromFile2 == null)
                break;

            JSONObject js1 = getResponse(stringFromFile1);
            JSONObject js2 = getResponse(stringFromFile2);

            // In case you would like to display responses...
            //System.out.println(js1.toString());
            //System.out.println(js2.toString());

            if (compareJSons(js1, js2)) {
                System.out.println(Colours.CYAN.getValue() + stringFromFile1 + Colours.GREEN.getValue() + " equals " + Colours.CYAN.getValue() + stringFromFile2 + Colours.DEFAULT.getValue());
            } else {
                System.out.println(Colours.CYAN.getValue() + stringFromFile1 + Colours.RED.getValue() + " not equals " + Colours.CYAN.getValue() + stringFromFile2 + Colours.DEFAULT.getValue());
            }
        }
    }
}
