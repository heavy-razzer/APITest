package fileManager;

import utils.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {

    private BufferedReader bufferedReader;

    public FileManager(String fileName) {

        String rootDirectory = System.getProperty("user.dir");
        String filePathString = rootDirectory + "/files/" + fileName;

        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(filePathString));
        } catch (FileNotFoundException e) {
            Log.errLog("File '" + fileName + "' not found");
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
