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
     * @param action  the action performed on the file (encrypt, decrypt, brute_force)
     */
    public void saveFile(ArrayList<String> strings, Actions action) throws IOException {
        String newFilePath = getNewFilePath(filePath, action);
        FileWriter fileWriter = new FileWriter(newFilePath);

        for (String line : strings) {
            fileWriter.write(line + System.lineSeparator());
        }

        fileWriter.close();

        RandomAccessFile randomAccessFileObject = new RandomAccessFile(newFilePath, "rw");
        long length = randomAccessFileObject.length() - 2;
        randomAccessFileObject.setLength(length);
        randomAccessFileObject.close();
    }

    /**
     * Generates a new file path based on the specified old file path and action.
     *
     * @param oldFilePath the old file path
     * @param action      the action performed on the file (encrypt, decrypt, brute_force)
     * @return the new file path with action modifier added
     */
    public String getNewFilePath(String oldFilePath, Actions action) {
        int dotIndex = oldFilePath.lastIndexOf(".");
        int actionIndex = oldFilePath.lastIndexOf("[");
        String newFilePath;

        if (action.toString().equalsIgnoreCase(String.valueOf(Actions.ENCRYPT))) {
            newFilePath = oldFilePath.substring(0, dotIndex) + "[" +
                    Actions.ENCRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
        } else if (action.toString().equalsIgnoreCase(String.valueOf(Actions.DECRYPT))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFilePath = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFilePath = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else if (action.toString().equalsIgnoreCase(String.valueOf(Actions.BRUTE_FORCE))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFilePath = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFilePath = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else {
            newFilePath = oldFilePath.substring(0, dotIndex) + "[ERR]" + oldFilePath.substring(dotIndex);
        }

        return newFilePath;
    }
}
