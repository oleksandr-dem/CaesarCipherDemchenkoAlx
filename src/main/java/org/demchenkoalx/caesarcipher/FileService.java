package org.demchenkoalx.caesarcipher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileService {

    private String filePath;

    /**
     * Retrieves the file path of the original file.
     *
     * @return the current file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path to the original file.
     *
     * @param filePath the new file path to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the contents of the file specified by the file path.
     *
     * @return an ArrayList of strings representing the lines of the file
     */
    public ArrayList<String> readFile() throws IOException {
        return (ArrayList<String>) Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    }

    /**
     * Saves the given strings to a file with a modified file path based on the specified action.
     *
     * @param strings the strings to save to the file
     * @param action  the action performed on the file (encrypt, decrypt, brute force)
     */
    public void saveFile(ArrayList<String> strings, String action) throws IOException {
        FileWriter fileWriter = new FileWriter(getNewFilePath(filePath, action));

        for (String line : strings) {
            fileWriter.write(line + System.lineSeparator());
        }

        fileWriter.close();
    }

    /**
     * Generates a new file path based on the specified old file path and action.
     *
     * @param oldFilePath the old file path
     * @param action      the action performed on the file (encrypt, decrypt, brute force)
     * @return the new file path with action modifier added
     */
    public String getNewFilePath(String oldFilePath, String action) {
        int dotIndex = oldFilePath.lastIndexOf(".");
        int actionIndex = oldFilePath.lastIndexOf("[");
        String newFileName;

        if (action.equalsIgnoreCase(String.valueOf(Actions.ENCRYPT))) {
            newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                    Actions.ENCRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
        } else if (action.equalsIgnoreCase(String.valueOf(Actions.DECRYPT))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFileName = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else if (action.equalsIgnoreCase(String.valueOf(Actions.BRUTE_FORCE))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFileName = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else {
            newFileName = oldFilePath.substring(0, dotIndex) + "[ERR]" + oldFilePath.substring(dotIndex);
        }

        return newFileName;
    }
}
