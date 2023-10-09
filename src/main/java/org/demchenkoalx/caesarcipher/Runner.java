package org.demchenkoalx.caesarcipher;

import java.io.IOException;

public class Runner {

    ConsoleClient cli;
    FileService fileService;
    CaesarCipher cryptographer;

    public Runner(FileService fileService, CaesarCipher cryptographer) {
        this.fileService = fileService;
        this.cryptographer = cryptographer;
        this.cli = new ConsoleClient();
    }

    /**
     * Runs the program with the given command-line arguments in case that arguments were entered with the start of
     * the program else runs console client instead.
     *
     * @param args the command-line arguments passed to the program
     */
    public void run(String[] args) throws IOException {
        if (args.length >= 2) {
            String action = args[0];
            fileService.setFilePath(args[1]);

            try {
                if (action.equalsIgnoreCase(Actions.ENCRYPT.toString()) && args.length > 2) {
                    int offset = Integer.parseInt(args[2]);
                    cryptographer.encrypt(fileService, offset);
                } else if (action.equalsIgnoreCase(Actions.DECRYPT.toString()) && args.length > 2) {
                    int offset = Integer.parseInt(args[2]);
                    cryptographer.decrypt(fileService, offset);
                } else if (action.equalsIgnoreCase(Actions.BRUTE_FORCE.toString())) {
                    cryptographer.bruteForce(fileService);
                } else {
                    System.out.println("""

                             >>>>>>> Invalid action or offset arguments entered.
                             >>>>>>> Starting console client.\
                            """);
                    cli.run(fileService, cryptographer);
                }
            } catch (IOException e) {
                System.out.println("\n >>>>>>> Invalid filepath: " + e.getMessage() +
                        "\n >>>>>>> Starting console client.");
                cli.run(fileService, cryptographer);
            }
        } else {
            System.out.println("\nWelcome User!\nThis is Cipher Caesar Cryptographer.");
            cli.run(fileService, cryptographer);
        }
    }
}
