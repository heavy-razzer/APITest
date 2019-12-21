import fileManager.FileManager;
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
            System.out.println(stringFromFile1 + "    " + stringFromFile2);
        }
    }
}
