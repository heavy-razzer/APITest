import httpCommands.HttpCommands;
import managers.FileManager;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static managers.JsonManager.convertToTreeMap;
import static managers.JsonManager.jsonToMap;

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

            JSONObject js1 = HttpCommands.getResponceJSON(stringFromFile1);
            JSONObject js2 = HttpCommands.getResponceJSON(stringFromFile2);
            String json1 = js1.toString();
            String json2 = js2.toString();
            System.out.println(json1);
            System.out.println(json2);

            compareJSons(js1, js2);

            System.out.println("===============");
        }
    }

    public boolean compareJSons(JSONObject js1, JSONObject js2) {

        boolean result = false;

        // Compare string jsons as is
        if (js1.toString().equals(js2.toString()))
            return true;

        // Build map from json
        Map jsMap1 = jsonToMap(js1);
        Map jsMap2 = jsonToMap(js2);

        Map treeMap1 = convertToTreeMap(jsMap1);
        Map treeMap2 = convertToTreeMap(jsMap2);

        //if (jsMap1.entrySet().toArray().length != jsMap2.entrySet().toArray().length) return false;

        Object[] js1Array = jsMap1.entrySet().toArray();
        Object[] js2Array = jsMap2.entrySet().toArray();

        Object[] tree1Array = treeMap1.entrySet().toArray();
        Object[] tree2Array = treeMap2.entrySet().toArray();

        // If number of items on first level in jsons are not same, then return false
        //if (js1Array.length != js2Array.length) return false;

        //Arrays.sort(js1Array);
        //Arrays.sort(js2Array);

        System.out.println("---");
        for (Object arrayItem : js1Array) {
            System.out.println(arrayItem.toString());
        }
        System.out.println(" ");
        for (Object arrayItem : tree1Array) {
            System.out.println(arrayItem.toString());
        }

        System.out.println("---");
        for (Object arrayItem : js2Array) {
            System.out.println(arrayItem.toString());
        }
        System.out.println(" ");
        for (Object arrayItem : tree2Array) {
            System.out.println(arrayItem.toString());
        }

        return result;
    }


}
