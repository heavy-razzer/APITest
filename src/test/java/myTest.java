import fileManager.FileManager;
import httpCommands.HttpCommands;
import org.json.JSONObject;
import org.junit.Test;

public class myTest {

    @Test
    public void test() {

        FileManager file1 = new FileManager("file1.txt");
        FileManager file2 = new FileManager("file2.txt");

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
            System.out.println("===============");


        }
    }
}
