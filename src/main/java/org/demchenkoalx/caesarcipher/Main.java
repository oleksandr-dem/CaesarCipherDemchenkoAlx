package org.demchenkoalx.caesarcipher;

import java.io.IOException;

public class Main {

    /**
     * The entry point of the program.
     *
     * @param args the command-line arguments passed to the program from the command-line
     */
    public static void main(String[] args) throws IOException {
        Constants constants = new Constants();
        FileService fileService = new FileService();
        CaesarCipher cryptographer =  new CaesarCipher(constants);
        Runner runner = new Runner(fileService, cryptographer);
        runner.run(args);
    }
}
