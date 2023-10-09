package org.demchenkoalx.caesarcipher;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleClient {

    /**
     * Runs the console interface of the program.
     *
     * @param fileService   the FileService object used for file operations
     * @param cryptographer the CaesarCipher object used for encryption and decryption
     */
    public void run(FileService fileService, CaesarCipher cryptographer) throws IOException {
        int offset;
        Constants constants = cryptographer.constants;
        Scanner scanner = new Scanner(System.in);
        System.out.print(constants.MAIN_MENU);
        int actionNumber = 0;

        if (scanner.hasNextInt()) {
            actionNumber = scanner.nextInt();
        } else {
            System.out.println(constants.WRONG_ACTION);
            run(fileService, cryptographer);
        }

        switch (actionNumber) {
            case 1 -> {
                try {
                    System.out.print(constants.ENCRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    System.out.print(constants.OFFSET_MENU);
                    offset = scanner.nextInt();
                    cryptographer.encrypt(fileService, offset);
                    System.out.println(constants.FILE_SAVED);
                } catch (IOException e) {
                    printFileNotFoundException(e);
                    run(fileService, cryptographer);
                }
            }
            case 2 -> {
                try {
                    System.out.print(constants.DECRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    System.out.print(constants.OFFSET_MENU);
                    offset = scanner.nextInt();
                    cryptographer.decrypt(fileService, offset);
                    System.out.println(constants.FILE_SAVED);
                } catch (IOException e) {
                    printFileNotFoundException(e);
                    run(fileService, cryptographer);
                }
            }
            case 3 -> {
                try {
                    System.out.print(constants.DECRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    cryptographer.bruteForce(fileService);
                    System.out.println(constants.FILE_SAVED);
                } catch (IOException e) {
                    printFileNotFoundException(e);
                    run(fileService, cryptographer);
                }
            }
            default -> {
                System.out.println(constants.WRONG_ACTION);
                run(fileService, cryptographer);
            }
        }

        scanner.close();
    }

    public void printFileNotFoundException(IOException e) {
        System.out.println("\n >>>>>>> File not found: " + e.getMessage());
    }
}
