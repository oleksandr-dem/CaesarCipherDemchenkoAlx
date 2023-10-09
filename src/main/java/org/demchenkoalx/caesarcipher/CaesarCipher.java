package org.demchenkoalx.caesarcipher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CaesarCipher {

    Constants constants;
    ArrayList<String> originalLinesList;
    ArrayList<String> resultLinesList;

    public CaesarCipher(Constants constants) {
        this.constants = constants;
    }

    /**
     * Applies the Caesar cipher to the given line of text with the specified offset.
     *
     * @param cipherLine the line of text to be ciphered
     * @param offset     the offset used for shifting characters in the alphabet
     * @return the ciphered text
     */
    public String cipher(String cipherLine, int offset) {
        StringBuilder result = new StringBuilder();

        for (char ch : cipherLine.toCharArray()) {
            int charIndex = constants.ALPHABET_EN.indexOf(ch);
            if (charIndex < 0) {
                result.append(ch);
            } else {
                result.append(constants.ALPHABET_EN.charAt((charIndex + offset) % constants.getAlphabetLength()));
            }
        }
        return result.toString();
    }

    /**
     * Encrypts the contents of the file specified by the FileService object using the specified offset.
     * The encrypted result is saved to a new file.
     *
     * @param fileService the FileService object for reading and saving files
     * @param offset      the offset used for encrypting the file
     */
    public void encrypt(FileService fileService, int offset) throws IOException {
        originalLinesList = new ArrayList<>(fileService.readFile());
        resultLinesList = new ArrayList<>();
        for (String line : originalLinesList) {
            resultLinesList.add(cipher(line, Math.abs(offset) % constants.getAlphabetLength()));
        }
        fileService.saveFile(resultLinesList, String.valueOf(Actions.ENCRYPT));
    }

    /**
     * Decrypts the contents of the file specified by the FileService object using the specified offset.
     * The decrypted result is saved to a new file.
     *
     * @param fileService the FileService object for reading and saving files
     * @param offset      the offset used for decrypting the file
     */
    public void decrypt(FileService fileService, int offset) throws IOException {
        originalLinesList = new ArrayList<>(fileService.readFile());
        resultLinesList = new ArrayList<>();
        for (String line : originalLinesList) {
            resultLinesList.add(cipher(line, constants.getAlphabetLength() -
                    (Math.abs(offset) % constants.getAlphabetLength())));
        }
        fileService.saveFile(resultLinesList, String.valueOf(Actions.DECRYPT));
    }

    /**
     * Performs a brute-force attack on the file specified by the FileService object to decrypt its contents.
     * The decrypted result is saved to a new file if common words are found.
     *
     * @param fileService the FileService object for reading and saving files
     */
    public void bruteForce(FileService fileService) throws IOException {
        int offset;
        Scanner scanner = new Scanner(new File(fileService.getFilePath()));
        ArrayList<String> wordsList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordsList.add(scanner.next());
        }
        scanner.close();

        ArrayList<String> tempListOfWordsWithOffset = new ArrayList<>();
        for (offset = 1; offset < constants.getAlphabetLength(); offset++) {
            tempListOfWordsWithOffset.clear();
            for (String singleWord : wordsList) {
                tempListOfWordsWithOffset.add(cipher(singleWord,
                        constants.getAlphabetLength() - (offset % constants.getAlphabetLength())));
            }

            for (String word : tempListOfWordsWithOffset) {
                for (String commonWord : constants.COMMON_WORDS) {
                    if (word.equals(String.valueOf(commonWord))) {
                        originalLinesList = new ArrayList<>(fileService.readFile());
                        resultLinesList = new ArrayList<>();
                        for (String line : originalLinesList) {
                            resultLinesList.add(cipher(line,
                                    (constants.getAlphabetLength()) - (offset % (constants.getAlphabetLength()))));
                        }
                        fileService.saveFile(resultLinesList, String.valueOf(Actions.BRUTE_FORCE));
                        break;
                    }
                }
            }
        }
    }
}
