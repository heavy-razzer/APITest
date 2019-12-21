package managers;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Class for all operations with data file: read, get data, handle errors
 */
public class FileManager {

    private BufferedReader bufferedReader;

    public FileManager(String fileName) {

        String rootDirectory = System.getProperty("user.dir");
        String filePathString = rootDirectory + "/files/" + fileName;

        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(filePathString));
        } catch (FileNotFoundException e) {
            Assert.fail("File '" + fileName + "' not found");
        }
    }

    public String getNextLine() {

        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
