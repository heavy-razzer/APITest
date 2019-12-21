import managers.FileManager;
import org.json.JSONObject;
import org.junit.Test;

import static httpCommands.HttpCommands.getResponceJSON;
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

            JSONObject js1 = getResponceJSON(stringFromFile1);
            JSONObject js2 = getResponceJSON(stringFromFile2);
            String json1 = js1.toString();
            String json2 = js2.toString();
            System.out.println(json1);
            System.out.println(json2);

            compareJSons(js1, js2);

            System.out.println("===============");
        }
    }
}
